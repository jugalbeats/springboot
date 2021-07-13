package com.example.jugalbeats.controllers;

import com.example.jugalbeats.config.security.Authorize;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.MusicianForm;
import com.example.jugalbeats.pojo.MusicianResponse;
import com.example.jugalbeats.pojo.RegistrationForm;
import com.example.jugalbeats.pojo.model.PriceInfo;
import com.example.jugalbeats.services.MusicianDashboardServices;
import com.example.jugalbeats.utils.Constants;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/musician")
public class MusicianDashBoardController {

    @Autowired
    private MusicianDashboardServices musicianDashboardServices;

    @PostMapping("/data")
    public ApiResponse register(@RequestBody MusicianForm musicianForm) {
        try {
            return musicianDashboardServices.addData(musicianForm);
        } catch (Exception e) {
            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
        }
    }
    @GetMapping("/data/{username}")
    @Authorize
    public  ApiResponse getMusician( @PathVariable("username") String username) {
    	ApiResponse musician= musicianDashboardServices.getMusiciandata(username);
    	if(Objects.nonNull(musician)) {
   	     return musician;}
        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
    	
        
    }
    
    @PutMapping("/data/{username}")
    public ApiResponse updateData(@RequestBody MusicianForm musicianForm,@PathVariable("username") String username,@RequestParam(required=false, defaultValue="false") boolean isPriceListAdd) {
        try {
            return musicianDashboardServices.updateData(musicianForm,username,isPriceListAdd);
        } catch (Exception e) {
            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
        }
    }
    
    @DeleteMapping("/price/{username}")
    public ApiResponse deletePrice(@RequestBody PriceInfo price,@PathVariable("username") String username) {
        try {
            return musicianDashboardServices.deletePrice(price,username);
        } catch (Exception e) {
            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
        }
    }




}
