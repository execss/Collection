package com.example.jetty.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {
    @GetMapping
    String t1() {
        return "okokok";
    }
}
