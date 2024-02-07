package com.getstarted.firstmangoapp.repository;

import com.getstarted.firstmangoapp.model.Users;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users,Long>{
    
}
