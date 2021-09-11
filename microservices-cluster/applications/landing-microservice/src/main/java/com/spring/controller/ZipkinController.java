package com.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
class ZipkinController {

    @Value("${database.microservice.fake.api.url}")
    String databaseMicroserviceFakeAPIURL;

    @Autowired
    @Qualifier("simple-rest-client")
    RestTemplate restTemplate;

    @RequestMapping(value = "/zipkin", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONArray> zipkinService() {
        log.info("Inside zipkinService()");
        JSONArray response = null;
        try {
            response = restTemplate.getForObject(databaseMicroserviceFakeAPIURL, JSONArray.class);
        } catch (Exception e) {
            log.error("Exception Inside zipkinService() : {}", e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }
}