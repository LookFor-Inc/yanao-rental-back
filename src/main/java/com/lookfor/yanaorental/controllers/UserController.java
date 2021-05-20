package com.lookfor.yanaorental.controllers;

import com.lookfor.json.schemas.generated.OkResponse;
import com.lookfor.json.schemas.generated.user.*;
import com.lookfor.yanaorental.annotations.CurrentUserId;
import com.lookfor.yanaorental.annotations.ExtendedEmailValidator;
import com.lookfor.yanaorental.services.user.UserDtoConverter;
import com.lookfor.yanaorental.services.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Validated
@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoConverter dtoConverter;

    @GetMapping()
    public UserInfoResponse getUserInfo(@CurrentUserId Long userId) {
        return userService.fetchById(userId, dtoConverter::convertToUserInfo);
    }

    @PutMapping("/info")
    public UserInfoUpdateResponse updateUserInfo(
            @RequestBody @Valid UserInfoUpdateRequest request,
            @CurrentUserId Long userId
    ) {
        return userService.updateInfo(userId, request);
    }

    @PutMapping("/avatar")
    public UserAvatarUpdateResponse updateUserAvatar(
            @RequestPart MultipartFile avatar,
            @CurrentUserId Long userId
    ) throws IOException {
        return userService.updateAvatar(userId, avatar);
    }

    @PutMapping("/password/update")
    public OkResponse changeUserPassword(
            @RequestBody @Valid UserPasswordUpdateRequest request,
            @CurrentUserId Long userId
    ) {
        userService.updatePassword(userId, request);
        return new OkResponse("Password changed successfully");
    }

    @PostMapping("/check-email")
    public OkResponse checkEmailForUniqueness(@ExtendedEmailValidator @RequestParam String email) {
        userService.isEmailUnique(email);
        return new OkResponse("Email " + email + " available");
    }
}
