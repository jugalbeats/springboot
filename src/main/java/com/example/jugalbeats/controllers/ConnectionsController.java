package com.example.jugalbeats.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.config.security.Authorize;
import com.example.jugalbeats.exception.UnauthorizedException;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.BookingRequest;
import com.example.jugalbeats.services.ConnectionsService;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/connection")
public class ConnectionsController {
	
	
	@Autowired
	ConnectionsService service;
	
	@PostMapping("/add")
	@Authorize
	public ApiResponse addFriend(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), sender);
		ApiResponse response = service.postAddFriend(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@PostMapping("/accept")
	@Authorize
	public ApiResponse acceptRequest(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver,HttpServletRequest httpRequest ) throws UnauthorizedException {
		//Utils.matchString(httpRequest.getAttribute("username").toString(), receiver);
		ApiResponse response = service.postAcceptRequest(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	@PostMapping("/reject")
	@Authorize
	public ApiResponse rejectRequest(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), receiver);
		ApiResponse response = service.postRejectRequest(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@DeleteMapping("/remove-friend")
	@Authorize
	public ApiResponse removeFriend(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver) {
		ApiResponse response = service.removeFriend(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@DeleteMapping("/remove-friend-request")
	@Authorize
	public ApiResponse removeFriendRequest(@RequestParam(required=true)String sender, @RequestParam(required=true)String receiver,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), sender);
		ApiResponse response = service.removeFriendRequest(sender, receiver);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@GetMapping("/friend-list")
	//@Authorize
	public ApiResponse getFriendList(@RequestParam(required=true)String username,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = service.getFriendsList(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@GetMapping("/pending-request/receive")
	@Authorize
	public ApiResponse getPendingReceiveRequest(@RequestParam(required=true)String username,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = service.getPendingRecievedRequest(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	@GetMapping("/pending-request")
	@Authorize
	public ApiResponse getPendingRequest(@RequestParam(required=true)String username,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = service.getPendingSentRequest(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@PostMapping("/follow")
	public ApiResponse followUser(@RequestParam(name = "follower") String follower, @RequestParam(name = "following") String following){
		ApiResponse response = service.saveFollower(follower, following);
		return response;
	}

}
