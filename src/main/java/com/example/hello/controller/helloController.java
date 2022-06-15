package com.example.hello.controller;

import com.example.hello.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gateway")
public class helloController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello1")
    public String getHello()  {
        if (true) {
            return "Hello 1";
        } else {
            return "hellooo 1";
        }
    }

    @GetMapping("/hello2")
    public String hello2()  {
        return restTemplate.getForObject("http://hello2/hello2", String.class);
    }

    @GetMapping("/hello3")
    public String hello3()  {
        return restTemplate.getForObject("http://hello3/hello3", String.class);
    }

    @GetMapping("/mail") // sends mail only when connected to personal network and not Maveric network as its accessing gmail
    public String triggerMail()  {
        emailSenderService.sendSimpleEmail("secularyoung8@gmail.com",
                "hello hello", "testing");
        System.out.println("Mail is sent");
        return "Mail is sent";
    }
}
