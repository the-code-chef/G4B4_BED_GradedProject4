package com.gl.gp4.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gl.gp4.constants.ApplicationConstants.SERVER_UP_RUNNING;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<String> getHealthCheck(){
        return ResponseEntity.ok().body(SERVER_UP_RUNNING);
    }
}
