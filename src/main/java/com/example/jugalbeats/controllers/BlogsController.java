package com.example.jugalbeats.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.models.Blogs;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.services.BlogsService;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/blogs")
public class BlogsController {

	@Autowired
	private BlogsService blogsService;

	@PostMapping
	public ApiResponse createJob(@RequestBody Blogs blog) {
		ApiResponse response = blogsService.postBlog(blog);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping
	public ApiResponse getJobs() {
		ApiResponse response = blogsService.getAllBlogs();
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}
}
