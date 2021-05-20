package com.lookfor.yanaorental.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class RootController {
    private static final String WELCOME_MSG = "Welcome to YANAO Rental service!";

    @GetMapping
    public String index() {
        log.info(WELCOME_MSG);
        return WELCOME_MSG;
    }

    @GetMapping("/ping")
    public String pong() {
        return "PONG";
    }
}
