package com.getstarted.firstmangoapp.model;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class Users {
    
    @Id
    private Long cps_no ;
    private String name;
    private String fatherName;
    private LocalDate dob;
    private String mobileNo;

    

    public Users() {
    }

    
    public Users(Long cps_no, String name, String fatherName, LocalDate dob, String mobileNo) {
        this.cps_no = cps_no;
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.mobileNo = mobileNo;
    }


    public Long getCps_no() {
        return cps_no;
    }
    public void setCps_no(Long cps_no) {
        this.cps_no = cps_no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }    
}
