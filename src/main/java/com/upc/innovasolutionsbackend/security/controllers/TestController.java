package com.upc.innovasolutionsbackend.security.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "${ip.frontend}")
@CrossOrigin(origins = "${ip.frontend}", allowCredentials = "true", exposedHeaders = "Authorization") //para cloud
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('PROFESOR')")
    public String adminEndpoint() {
        return "This is the admin endpoint, accessible only to users with PROFESOR role.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('PROFESOR', 'PADRE', 'ALUMNO')")
    public String userEndpoint() {
        return "This is the user endpoint, accessible to users with PROFESOR, PADRE or ALUMNO role.";
    }
}
