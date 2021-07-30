package com.example.jugalbeats.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.config.security.Authorize;
import com.example.jugalbeats.exception.UnauthorizedException;
import com.example.jugalbeats.models.JobPost;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.ClientForm;
import com.example.jugalbeats.pojo.ClientResponse;
import com.example.jugalbeats.services.ClientDasboardService;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.JwtTokenUtil;
import com.example.jugalbeats.utils.Utils;

/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/client")
public class ClientDashboardController {

	@Autowired
	private ClientDasboardService clientService;

	@PostMapping
	@Authorize
	public ApiResponse register(@RequestBody ClientForm clientForm, HttpServletRequest request) throws UnauthorizedException {
		
		Utils.matchString(request.getAttribute("username").toString(), clientForm.getUserName());
		ApiResponse response = clientService.addData(clientForm);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/data/{username}")
	@Authorize
	public ApiResponse getMusician(@PathVariable("username") String username, HttpServletRequest request) throws UnauthorizedException {
		Utils.matchString(request.getAttribute("username").toString(), username);
		ApiResponse response = clientService.getClientDashboard(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@PutMapping
	@Authorize
	public ApiResponse updateClientDashboard(@RequestBody ClientResponse request,HttpServletRequest httpRequest) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), request.getUserName());
		ApiResponse response = clientService.updateData(request);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
}
