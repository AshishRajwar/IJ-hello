package com.example.hello.controller;

import com.example.hello.model.AuthenticationRequest;
import com.example.hello.model.AuthenticationResponse;
import com.example.hello.service.EmailSenderService;
import com.example.hello.service.MyUserDetailService;
import com.example.hello.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gateway")
public class helloController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService userDetailService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception  {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
                    throw new Exception("Incorrect credentials", e);
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

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
        return restTemplate.getForObject("http://secondservice/hello2", String.class);
    }

    @GetMapping("/hello3")
    public String hello3()  {
        return restTemplate.getForObject("http://thirdservice/hello3", String.class);
    }

    @GetMapping("/mail") // sends mail only when connected to personal network and not Maveric network as its accessing gmail
    public String triggerMail()  {
        emailSenderService.sendSimpleEmail("secularyoung8@gmail.com",
                "hello hello", "testing");
        System.out.println("Mail is sent");
        return "Mail is sent";
    }
}
