package com.buddylogin.demo.email;


// this interface is used to switch implementations

public interface EmailSender {
    void send(String to, String email);
}
