package com.example.jugalbeats.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.LoginRequest;
import com.example.jugalbeats.pojo.RegistrationForm;
import com.example.jugalbeats.services.RegistrationAndLoginServices;
import com.example.jugalbeats.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import java.security.Principal;
import java.util.Objects;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/user")
public class RegistrationAndLoginController {

    @Autowired
    private RegistrationAndLoginServices registrationAndLoginServices;

    @PostMapping("/registration")
    public ApiResponse register(@RequestBody RegistrationForm registrationForm) {
        try {
            return registrationAndLoginServices.registrationUser(registrationForm);
        } catch (Exception e) {
            return new ApiResponse(Constants.INTERNAL_SERVER_ERROR_CODE, Constants.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }
    
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            return registrationAndLoginServices.loginUser(loginRequest);
        } catch (Exception e) {
            return new ApiResponse(Constants.INTERNAL_SERVER_ERROR_CODE, Constants.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }
    @DeleteMapping
    public ApiResponse deleteUser(@RequestParam(required=true)String username,@RequestParam(required=true) String password ) {
    	 ApiResponse response=registrationAndLoginServices.deleteUser(username, password);
         if(Objects.nonNull(response)) {
        	 return response;
         }
        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
    
    }
    
    @PutMapping("/{username}")
    public ApiResponse updateUser(@RequestBody RegistrationForm registrationForm,@PathVariable("username") String username) {
    	 ApiResponse response=registrationAndLoginServices.updateUser(registrationForm,username);
         if(Objects.nonNull(response)) {
        	 return response;
         }
        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
    
    }

}
