package com.lookfor.yanaorental.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String host;
    private String front;
    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();

    @Getter
    @Setter
    public static class Auth {
        private String jwtTokenSecret;
        private int jwtTokenExpiration;
        private final Cookie cookie = new Cookie();

        @Getter
        @Setter
        public static class Cookie {
            private String name;
            private boolean secure;
        }
    }

    @Getter
    @Setter
    public static final class OAuth2 {
        private List<String> authorizedRedirectUrls;
    }
}
