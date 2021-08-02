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
import com.example.jugalbeats.pojo.BookingRequest;
import com.example.jugalbeats.services.BookingService;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
    @Authorize
	public ApiResponse createBooking(@RequestBody BookingRequest booking,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), booking.getUsernameClient());
		
		ApiResponse response = bookingService.createBooking(booking);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/{username}")
	@Authorize
	public ApiResponse getBooking(@PathVariable("username") String username,
			@RequestParam(required = true, name = "userType") String userType,
			@RequestParam (required =  false,name="dateTime")Long dateTime,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = bookingService.getAllBooking(username,userType,dateTime);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	@PutMapping("/{username}/{bookingId}")
	@Authorize
	public ApiResponse updateBooking(@PathVariable("username") String username,@PathVariable("bookingId") long bookingId,@RequestBody BookingRequest booking,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = bookingService.updateBooking(username,bookingId,booking);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}


}
