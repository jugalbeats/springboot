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

import com.example.jugalbeats.models.Blogs;
import com.example.jugalbeats.models.UserAvailability;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.AvailabilityRequest;
import com.example.jugalbeats.services.AvailabilityService;
import com.example.jugalbeats.utils.Constants;

/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/availability")
public class AvailabilityController {

	@Autowired
	AvailabilityService availabilityService;
	
	@PostMapping("/{username}")
	public ApiResponse setAvailability(@RequestBody AvailabilityRequest request,@PathVariable("username") String username ) {
		ApiResponse response = availabilityService.setAvailability(request,username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/{username}")
	public ApiResponse getAvailability(@PathVariable("username") String username) {
		ApiResponse response = availabilityService.getAvailability(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@PutMapping("/{username}")
	public ApiResponse updateAvailability(@RequestBody AvailabilityRequest request,@PathVariable("username") String username) {
		ApiResponse response = availabilityService.updateAvailability(request,username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
}
