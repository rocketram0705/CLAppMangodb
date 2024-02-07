package com.getstarted.firstmangoapp.model;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {
    
    @Id
    private Long    cpsNo;
    private String  name;
    private String  fatherName;
    private LocalDate   dob;
    private String  mobileNo;     

}
