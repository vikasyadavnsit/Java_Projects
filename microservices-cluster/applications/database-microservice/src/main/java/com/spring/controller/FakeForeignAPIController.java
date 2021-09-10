package com.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@Slf4j
class FakeForeignAPIController {

    @Value("${foreign.get.fake.rest.api}")
    String foreignGetFakeRestAPIURL;

    @Autowired
    @Qualifier("simple-rest-client")
    RestTemplate restTemplate;

    @GetMapping(value = "/fake/api")
    public ResponseEntity<JSONArray> getFakeAPI() {
        log.info("Inside getFakeAPI()");
        JSONArray response = null;
        try {
            response = restTemplate.getForObject(new URI(foreignGetFakeRestAPIURL), JSONArray.class);
        } catch (Exception e) {
            log.error("Exception Inside getFakeAPI() : {}", e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }
}