package com.example.jugalbeats.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.BlogsRepository;
import com.example.jugalbeats.models.Blogs;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@Service
public class BlogsService {
	
	@Autowired
	private BlogsRepository blogsRepo;
	
	public ApiResponse postBlog(Blogs blog) {
		if(Objects.isNull(blog.getCreatedBy())) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Blog post unsuccessful! Created by can't be null");
		}
		blogsRepo.save(blog);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Your Blog has been posted successfully");	
	}
	
	public ApiResponse getAllBlogs() {
		
	    List<Blogs>blogs=(List<Blogs>) blogsRepo.findAll();
	    if(blogs.isEmpty())
	    	return new ApiResponse(Constants.NO_CONTENT_CODE, Constants.NO_CONTENT_MESSAGE,blogs);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,blogs);
		
	}
	

}
