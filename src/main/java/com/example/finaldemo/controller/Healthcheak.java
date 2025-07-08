package com.example.finaldemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcheak {
@GetMapping("/health")
     public String healthcheak(){
          return "   yo  agin yes boss your code is ok " ;
    }
}
