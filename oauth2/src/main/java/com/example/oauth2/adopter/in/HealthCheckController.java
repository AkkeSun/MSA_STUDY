package com.example.oauth2.adopter.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HealthCheckController {

    @GetMapping("/check")
    public String check(){
        return "success";
    }

}
