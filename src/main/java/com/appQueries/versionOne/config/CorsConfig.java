package com.appQueries.versionOne.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${API_FRONT}")
    private String apiFront;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configure CORS mappings for the "/api/**" endpoint
        registry.addMapping("/api/**")
                // Allow requests from "http://localhost:9000" origin
                .allowedOrigins("http://localhost:9000")
                //.allowedOrigins(apiFront)
                // Allow specified HTTP methods
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                // Allow specified headers in the request
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept");
    }

}
