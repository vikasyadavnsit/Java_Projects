package com.spring.controller;

import ngrok.api.NgrokApiClient;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingPongController {

    @Autowired
    private NgrokApiClient ngrok;

    public void someMethod() {
        // returns https tunnel URL or null in case ngrok is not running
        String httpsTunnelUrl = ngrok.getHttpsTunnelUrl();

        //returns http tunnel URL or null in case ngrok is not running
        String httpTunnelUrl = ngrok.getHttpTunnelUrl();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> pong() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ping", "pong");
        someMethod();
        return ResponseEntity.ok(jsonObject);
    }
}
