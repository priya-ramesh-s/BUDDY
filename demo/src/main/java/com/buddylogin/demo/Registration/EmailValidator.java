package com.buddylogin.demo.Registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service  // validates email
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {

        return true;
    }
}
