package com.lookfor.yanaorental.security.oauth2;

import com.lookfor.yanaorental.config.AppProperties;
import com.lookfor.yanaorental.exceptions.rest.BadRequestException;
import com.lookfor.yanaorental.security.jwt.JwtTokenProvider;
import com.lookfor.yanaorental.utils.CookieHelper;
import com.lookfor.yanaorental.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.lookfor.yanaorental.security.oauth2.OAuth2AuthorizationRequestRepository.REDIRECT_URI_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuth2AuthorizationRequestRepository OAuth2AuthorizationRequestRepository;
    private final CookieHelper cookieHelper;
    private final AppProperties appProperties;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        handle(request, response, authentication);
    }

    protected void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            log.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
            return;
        }

        clearAuthenticationAttributes(response);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_COOKIE_NAME)
                .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }

        String targetUrl = redirectUri.orElse(appProperties.getFront());

        String token = jwtTokenProvider.generateToken(authentication);
        cookieHelper.addAuthCookie(response, token);

        return targetUrl;
    }

    protected void clearAuthenticationAttributes(HttpServletResponse response) {
        OAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(response);
    }

    private boolean isAuthorizedRedirectUri(String url) {
        URI clientRedirectUrl = URI.create(url);

        return appProperties.getOauth2().getAuthorizedRedirectUrls()
                .stream()
                .anyMatch(authorizedRedirectUrl -> {
                    URI authorizedURI = URI.create(authorizedRedirectUrl);

                    return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUrl.getHost())
                            && authorizedURI.getPort() == clientRedirectUrl.getPort();
                });
    }
}
