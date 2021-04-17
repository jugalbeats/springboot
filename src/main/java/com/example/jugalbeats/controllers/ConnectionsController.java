package com.example.jugalbeats.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.BookingRequest;
import com.example.jugalbeats.services.ConnectionsService;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/connection")
public class ConnectionsController {
	
	
	@Autowired
	ConnectionsService service;
	
	@PostMapping("/add")
	public ApiResponse addFriend(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver) {
		ApiResponse response = service.postAddFriend(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@PostMapping("/accept")
	public ApiResponse acceptRequest(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver) {
		ApiResponse response = service.postAcceptRequest(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	@PostMapping("/reject")
	public ApiResponse rejectRequest(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver) {
		ApiResponse response = service.postRejectRequest(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@DeleteMapping("/remove-friend")
	public ApiResponse removeFriend(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver) {
		ApiResponse response = service.removeFriend(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@DeleteMapping("/remove-friend-request")
	public ApiResponse removeFriendRequest(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver) {
		ApiResponse response = service.removeFriendRequest(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@GetMapping("/friend-list")
	public ApiResponse getFriendList(@RequestParam(required=true)String username) {
		ApiResponse response = service.getFriendsList(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@GetMapping("/pending-request/receive")
	public ApiResponse getPendingReceiveRequest(@RequestParam(required=true)String username) {
		ApiResponse response = service.getPendingRecievedRequest(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	@GetMapping("/pending-request")
	public ApiResponse getPendingRequest(@RequestParam(required=true)String username) {
		ApiResponse response = service.getPendingSentRequest(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}	

}
