package com.lookfor.yanaorental.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@Configuration
@ConfigurationPropertiesScan("com.lookfor.yanaorental.config")
public class AppConfig {
}
