package com.dan.security_test.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restricted")
public class RestrictedController {

    @GetMapping
    public ResponseEntity<String> showStuff() {
        return ResponseEntity.ok("Inside restricted area");
    }
}
