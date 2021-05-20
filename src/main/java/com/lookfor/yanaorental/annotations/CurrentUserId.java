package com.lookfor.yanaorental.annotations;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Hidden
@Documented
@AuthenticationPrincipal
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUserId {
}
