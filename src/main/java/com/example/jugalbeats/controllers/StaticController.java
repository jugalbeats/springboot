package com.example.jugalbeats.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.services.StaticService;
import com.example.jugalbeats.utils.Constants;

/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/static")
public class StaticController {
	
	@Autowired
	private StaticService staticService;
	@GetMapping("/profession")
	public ApiResponse getProfessions() {
		ApiResponse response = staticService.findProfessionList();
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@GetMapping("/genre")
	public ApiResponse getGenres(@RequestParam(required=true) String profession) {
		ApiResponse response = staticService.findGenreList(profession);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	
	@PostMapping("/genre")
	public ApiResponse addNewGenre(@RequestParam(required=true) String profession,@RequestParam(required=true) String genre) {
		ApiResponse response = staticService.addNewGenre(profession, genre);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
}
