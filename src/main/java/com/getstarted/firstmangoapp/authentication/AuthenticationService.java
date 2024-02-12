package com.getstarted.firstmangoapp.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.getstarted.firstmangoapp.config.JWtService;
import com.getstarted.firstmangoapp.model.Role;
import com.getstarted.firstmangoapp.model.Users;
import com.getstarted.firstmangoapp.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWtService jWtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponses register(RegisterRequest request) {
       
        var user = Users.builder().cpsNo(Long.parseLong(request.getCpsNo()))
        .name(request.getFirstName())
        .fatherName(request.getFatherName())
        .emailId(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
        
         usersRepository.save(user);
         var jwtToken = jWtService.generateJwtToken(user);

        return AuthenticationResponses.builder()
        .token(jwtToken)
        .build();
    }

    public AuthenticationResponses authenticate(AuthenticationRequest request) {
       authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getCpsNo(),request.getPassword())
       );

       var user = usersRepository.findById(Long.parseLong(request.getCpsNo())).orElseThrow();
       var jwtToken = jWtService.generateJwtToken(user);

        return AuthenticationResponses.builder()
        .token(jwtToken)
        .build();
    }
    

}
