package com.spring.config;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.Logbook;

import static java.util.Collections.singleton;
import static org.zalando.logbook.BodyFilter.merge;
import static org.zalando.logbook.BodyFilters.defaultValue;
import static org.zalando.logbook.Conditions.exclude;
import static org.zalando.logbook.Conditions.requestTo;
import static org.zalando.logbook.json.JsonBodyFilters.replaceJsonStringProperty;

@Configuration
public class LogbookConfig {

    @Bean
    public BodyFilter bodyFilter() {
        return merge(
                defaultValue(),
                replaceJsonStringProperty(singleton("secret"), "XXX"));
    }

}
