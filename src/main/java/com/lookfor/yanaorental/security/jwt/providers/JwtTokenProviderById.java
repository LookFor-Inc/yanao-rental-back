package com.lookfor.yanaorental.security.jwt.providers;

import com.lookfor.yanaorental.config.AppProperties;
import com.lookfor.yanaorental.models.auth.UserPrincipal;
import com.lookfor.yanaorental.security.jwt.JwtTokenProvider;
import com.lookfor.yanaorental.utils.Base64Utils;
import com.lookfor.yanaorental.utils.DateUtils;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class JwtTokenProviderById implements JwtTokenProvider {
    private final AppProperties appProperties;

    @Override
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String commaSeparatedListOfAuthorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .claim("authorities", commaSeparatedListOfAuthorities)
                .setIssuedAt(DateUtils.getNow())
                .setExpiration(DateUtils.calculateExpiration(appProperties.getAuth().getJwtTokenExpiration()))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            log.error("Invalid JWT signature -> Message: " + e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: " + e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: " + e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: " + e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: " + e);
        } catch (Exception e) {
            log.error("JWT parse error -> Message: " + e);
        }

        return false;
    }

    @Override
    public Authentication getAuthenticationFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String commaSeparatedListOfAuthorities = (String) claims.get("authorities");
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);

        long userId = Long.parseLong(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }

    private Key getSecretKey() {
        return new SecretKeySpec(
                Base64Utils.decode(appProperties.getAuth().getJwtTokenSecret()),
                SignatureAlgorithm.HS512.getJcaName()
        );
    }
}
