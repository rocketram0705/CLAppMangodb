package com.getstarted.firstmangoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.getstarted.firstmangoapp.model.Users;
import com.getstarted.firstmangoapp.services.UserServices;


@RestController
@RequestMapping("/api/v1/user")
public class ClAppUsersController {
    
    @Autowired
    private UserServices userServices;

    ObjectMapper objectMapper = new ObjectMapper();
        

    @PostMapping("/createNewUser")
    public ResponseEntity<String> createNewUserEntity(Users user) throws Exception{
    
       Users newUser = userServices.createNewUser(user);
       objectMapper.registerModule(new JavaTimeModule());
       String response = objectMapper.writeValueAsString(newUser);
       System.out.println(response);
       return ResponseEntity.status(201).body(response);
    }
}
