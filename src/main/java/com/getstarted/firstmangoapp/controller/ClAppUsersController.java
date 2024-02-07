package com.getstarted.firstmangoapp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.getstarted.firstmangoapp.model.Users;
import com.getstarted.firstmangoapp.services.UserServices;


@RestController(value = "/")
public class ClAppUsersController {
    
    @Autowired
    private UserServices userServices;

   @RequestMapping(path ="/createNewUser") 
    public ResponseEntity<String> createNewUserEntity(@RequestParam("cpsNo") String cpsNo,@RequestParam("fname") String fatherName,@RequestParam("name") String name,@RequestParam("mobileNo") String mobileNo) throws Exception{
        
        System.out.println("inside the method...");
        Long cpsNoLong = Long.parseLong(cpsNo);
        LocalDate dob = LocalDate.of(1993,05,07);
        Users user = new Users(cpsNoLong,name,fatherName,dob,mobileNo);

       Users newUser = userServices.createNewUser(user);
       ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.registerModule( new JavaTimeModule());
       String response = objectMapper.writeValueAsString(newUser);
       System.out.println(response);
       return ResponseEntity.status(201).body("Sucess");
    }
}
