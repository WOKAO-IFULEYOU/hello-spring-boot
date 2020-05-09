package com.beyond.hello.spring.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AjaxTestController {
    //ajax
    @PostMapping("/demotest")
    public ResponseEntity<?> demo(){
        System.out.println("demo");
        return ResponseEntity.ok("success");
    }
}
