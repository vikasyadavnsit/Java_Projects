package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean("simple-rest-client")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
