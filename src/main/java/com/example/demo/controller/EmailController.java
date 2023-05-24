package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.EmailService;

@RestController
@RequestMapping("/email")
@CrossOrigin
public class EmailController 
{

    @Autowired
    private EmailService emailService;
    
    public EmailController(EmailService emailService)
	{
		this.emailService = emailService;
	}

    @PostMapping
    public ResponseEntity<String> sendEmail(
    		@RequestParam("accessToken") String accessToken,
            @RequestParam("userId") Long userId)
    {
        emailService.sendEosReport(accessToken, userId);
        return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
    }
}