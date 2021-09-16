package com.buddylogin.demo.Registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")     //localhost:8080/api/v1/registration : postman
@AllArgsConstructor

public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
        // token can be retrieved from terminal : select * from app_user; -> SELECT * FROM confirmation_token;
        //eg: localhost:8080/api/v1/registration/confirm?token=d7c374f3-03a0-4d1e-a6b4-7a6db2676a22
    }
}
