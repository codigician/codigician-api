package com.codigician.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health")
public class HealthController {
    @GetMapping
    public void getHealth(){
        System.out.println("Applications works..");
    }
}
