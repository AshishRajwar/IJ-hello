package com.example.hello.controller;

import com.example.hello.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/hello1")
    public String getHello()  {
        if (true) {
            return "Hello 11";
        } else {
            return "hellooo 1";
        }
    }

    @GetMapping("/mail")
    public String triggerMail()  {
        emailSenderService.sendSimpleEmail("secularyoung8@gmail.com",
                "hello hello", "testing");
        System.out.println("Mail is sent");
        return "Mail is sent";
    }
}
