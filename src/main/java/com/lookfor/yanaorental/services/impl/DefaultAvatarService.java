package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.config.AppProperties;
import com.lookfor.yanaorental.services.AvatarService;
import com.lookfor.yanaorental.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@RequiredArgsConstructor
public class DefaultAvatarService implements AvatarService {
    private final AppProperties appProperties;

    @Override
    public URL getAvatarUrl(String email) {
        return UrlUtils.stringToUrl(appProperties.getHost() + "/images/default_avatar.png");
    }
}
