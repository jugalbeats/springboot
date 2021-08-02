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
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.config.security.Authorize;
import com.example.jugalbeats.exception.UnauthorizedException;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.FAQsRequest;
import com.example.jugalbeats.services.FAQsService;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;

/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/faqs")
public class FAQsController {

	@Autowired
	FAQsService faqsService;

	@PostMapping()
	@Authorize
	public ApiResponse createFaq(@RequestBody FAQsRequest request,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), request.getUsername());
		ApiResponse response = faqsService.addFAQs(request);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@PutMapping
	@Authorize
	public ApiResponse updateFaq(@RequestBody FAQsRequest request,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), request.getUsername());
		ApiResponse response = faqsService.updateFAQs(request);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/{username}")
	public ApiResponse getFaq(@PathVariable("username") String username) {
		ApiResponse response = faqsService.getFAQs(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@DeleteMapping("/{username}/{faqid}")
	@Authorize
	public ApiResponse deleteFaq(@PathVariable("username") String username, @PathVariable("faqid") String faqid,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = faqsService.deleteFAQsById(username, Long.parseLong(faqid));
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
}
