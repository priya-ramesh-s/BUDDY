package com.buddylogin.demo.Registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class RegistrationRequest {          // User info required for registration
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
}
