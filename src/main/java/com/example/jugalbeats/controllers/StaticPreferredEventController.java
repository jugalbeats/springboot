package com.example.jugalbeats.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.models.PreferredEvents;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.services.PreferredEventsService;
import com.example.jugalbeats.utils.Constants;

/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/preferred-events")
public class StaticPreferredEventController {
	
	@Autowired
	PreferredEventsService preferredEventsService;

	@PostMapping
	public ApiResponse addEvents(@RequestBody PreferredEvents events) {
		ApiResponse response = preferredEventsService.postPreferredEvent(events);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping
	public ApiResponse getEventsList() {
		ApiResponse response = preferredEventsService.getPreferredEvent();
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
}
