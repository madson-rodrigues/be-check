package com.api.becheck.controllers;

import com.api.becheck.dtos.LoginDto;
import com.api.becheck.dtos.TokenDto;
import com.api.becheck.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<TokenDto> auth(@RequestBody @Validated LoginDto loginDTO){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        try{
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDto("Bearer", token));

        } catch (Exception e){
            throw new UsernameNotFoundException("Username not found") ;
        }
    }

}