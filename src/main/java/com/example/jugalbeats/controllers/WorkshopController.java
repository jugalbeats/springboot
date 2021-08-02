package com.example.jugalbeats.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.config.security.Authorize;
import com.example.jugalbeats.exception.UnauthorizedException;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.WorkshopRequest;
import com.example.jugalbeats.services.WorkshopService;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;

/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/workshop")
public class WorkshopController {

	@Autowired
	WorkshopService workshopService;

	 @PostMapping("/{username}")
	 @Authorize
	    public ApiResponse createWorkshop(@RequestBody WorkshopRequest request,@PathVariable("username") String username,HttpServletRequest httpRequest ) throws UnauthorizedException {
			Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		         ApiResponse response=workshopService.createWorkshop(request, username);
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    @GetMapping("/all")
	    public  ApiResponse getWorksshopAll( ) {
	         ApiResponse response=workshopService.getAllWorkshop();
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
	    @PutMapping("/{username}")
	    @Authorize
	    public  ApiResponse updateWorkshop(@RequestBody WorkshopRequest request, @PathVariable("username") String username,@RequestParam Long workId,HttpServletRequest httpRequest ) throws UnauthorizedException {
			Utils.matchString(httpRequest.getAttribute("username").toString(), username);
	         ApiResponse response=workshopService.updateWorkshop(workId, username, request);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
	    @DeleteMapping("/{username}")
	    @Authorize
	    public  ApiResponse deleteWork( @PathVariable("username") String username,@RequestParam Long workId,HttpServletRequest httpRequest ) throws UnauthorizedException {
			Utils.matchString(httpRequest.getAttribute("username").toString(), username);
	         ApiResponse response=workshopService.deleteWorkshop(workId, username);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
	    @PostMapping("/{username}/apply")
	    @Authorize
	    public  ApiResponse applyToWorkshop(@PathVariable("username") String username,@RequestParam Long workId,HttpServletRequest httpRequest ) throws UnauthorizedException {
			Utils.matchString(httpRequest.getAttribute("username").toString(), username);
	         ApiResponse response=workshopService.applyToWorkshop(workId, username);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
	    
	    @GetMapping("/{username}")
	    @Authorize
	    public  ApiResponse getWorksshop( @PathVariable("username") String username,HttpServletRequest httpRequest ) throws UnauthorizedException {
			Utils.matchString(httpRequest.getAttribute("username").toString(), username);
	         ApiResponse response=workshopService.getWorkshop(username);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
}
