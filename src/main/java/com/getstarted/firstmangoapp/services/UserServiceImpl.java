package com.getstarted.firstmangoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getstarted.firstmangoapp.model.Users;
import com.getstarted.firstmangoapp.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserServices{

    @Autowired
    private UsersRepository usersRepository;

   /* public UserServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }*/
    @Override
    public Users createNewUser(Users user) {
        return usersRepository.save(user);
    }
    
}
