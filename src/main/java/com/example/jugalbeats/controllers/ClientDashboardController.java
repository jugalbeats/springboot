package com.example.jugalbeats.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.models.JobPost;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.ClientForm;
import com.example.jugalbeats.pojo.ClientResponse;
import com.example.jugalbeats.services.ClientDasboardService;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/client")
public class ClientDashboardController {

	@Autowired
	private ClientDasboardService clientService;
	
	   @PostMapping
	    public ApiResponse register(@RequestBody ClientForm clientForm) {
		         ApiResponse response=clientService.addData(clientForm);
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    @GetMapping("/data/{username}")
	    public  ApiResponse getMusician( @PathVariable("username") String username) {
	    	 ApiResponse response=clientService.getClientDashboard(username);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
		   @PutMapping
		    public ApiResponse updateClientDashboard(@RequestBody ClientResponse request) {
			         ApiResponse response=clientService.updateData(request);
			         if(Objects.nonNull(response)) {
			        	 return response;
			         }
		            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
		        
		    }
}
