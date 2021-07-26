package com.example.jugalbeats.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.models.Question;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.AvailabilityRequest;
import com.example.jugalbeats.services.QuestionService;
import com.example.jugalbeats.utils.Constants;

/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@PostMapping
	public ApiResponse addQuestion(@RequestBody Question question) {
		ApiResponse response = questionService.postQuestion(question);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping
	public ApiResponse getQuestions() {
		ApiResponse response = questionService.getAllQuestion();
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
	@DeleteMapping
	public ApiResponse deleteQuestions(@RequestParam("quesid")Long quesId) {
		ApiResponse response = questionService.deleteQues(quesId);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
}
