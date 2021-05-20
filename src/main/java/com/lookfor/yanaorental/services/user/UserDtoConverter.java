package com.lookfor.yanaorental.services.user;

import com.lookfor.json.schemas.generated.user.UserAvatarUpdateResponse;
import com.lookfor.json.schemas.generated.user.UserInfoResponse;
import com.lookfor.json.schemas.generated.user.UserInfoUpdateResponse;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.utils.UrlUtils;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class UserDtoConverter {
    public UserInfoResponse convertToUserInfo(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getEmail(),
                user.getType().toString(),
                user.getUserInfo().getFirstName(),
                user.getUserInfo().getLastName(),
                user.getUserInfo().getMiddleName(),
                user.getUserInfo().getAvatar(),
                user.getUserInfo().getRegistrationDate()
        );
    }

    public UserInfoUpdateResponse convertToUserInfoUpdate(User user) {
        return new UserInfoUpdateResponse(
                user.getId(),
                user.getUserInfo().getFirstName(),
                user.getUserInfo().getLastName(),
                user.getUserInfo().getMiddleName()
        );
    }

    public UserAvatarUpdateResponse convertToUserAvatarUpdate(User user) {
        return new UserAvatarUpdateResponse(
                user.getId(),
                user.getUserInfo().getAvatar()
        );
    }

    public String convertToImageStringId(URL imageUrl) {
        String lastPart = UrlUtils.getLastPart(imageUrl);

        if (lastPart != null && lastPart.startsWith("https://www.gravatar.com")) {
            lastPart = null;
        }

        return lastPart;
    }
}
