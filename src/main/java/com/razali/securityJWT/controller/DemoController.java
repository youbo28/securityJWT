package com.razali.securityJWT.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")

public class DemoController {
    @GetMapping
    public ResponseEntity<String> sayHello(){

        return ResponseEntity.badRequest().body("Hello from secured endpoint");
    }
}
