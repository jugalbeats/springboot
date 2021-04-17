package com.example.jugalbeats.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.FAQsRequest;
import com.example.jugalbeats.services.FAQsService;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/faqs")
public class FAQsController {

	@Autowired
	FAQsService faqsService;
	 @PostMapping()
	    public ApiResponse createFaq(@RequestBody FAQsRequest request) {
		         ApiResponse response=faqsService.addFAQs(request);
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    @GetMapping("/{username}")
	    public  ApiResponse getFaq( @PathVariable("username") String username) {
	         ApiResponse response=faqsService.getFAQs(username);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
      return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
  
}
	    @DeleteMapping("/{username}/{jobid}")
	    public  ApiResponse deleteFaq( @PathVariable("username") String username,@PathVariable("jobid") String jobid) {
	    	 ApiResponse response=faqsService.deleteFAQsById(username,Long.parseLong(jobid));
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
        
    }
	    }
