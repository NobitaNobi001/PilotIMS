package com.pilot.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc configuration
 * @author ezuy
 * @date 21/1/19 19:48
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    /**
     * login interceptor
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
