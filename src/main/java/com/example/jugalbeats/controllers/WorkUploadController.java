package com.example.jugalbeats.controllers;

import java.util.Objects;


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

import com.example.jugalbeats.models.Comment;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.CommentRequest;
import com.example.jugalbeats.pojo.WorkUploadRequest;
import com.example.jugalbeats.services.WorkUploadService;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/work")
public class WorkUploadController {
	
	@Autowired
	WorkUploadService workUploadService;

	 @PostMapping
	    public ApiResponse createWork(@RequestBody WorkUploadRequest request) {
		         ApiResponse response=workUploadService.addWork(request);
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    @GetMapping("/{username}")
	    public  ApiResponse getWorks( @PathVariable("username") String username) {
	         ApiResponse response=workUploadService.getWork(username);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
	    @PutMapping("/{username}")
	    public  ApiResponse updateWork(@RequestBody WorkUploadRequest request, @PathVariable("username") String username,@RequestParam Long workId) {
	         ApiResponse response=workUploadService.updateWork(request, username, workId);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
	    @DeleteMapping("/{username}")
	    public  ApiResponse deleteWork( @PathVariable("username") String username,@RequestParam Long workId) {
	         ApiResponse response=workUploadService.deleteWork( username, workId);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
	    @GetMapping("/{username}/{workId}")
	    public  ApiResponse getWorkById( @PathVariable("username") String username,@PathVariable("workId") Long workId) {
	         ApiResponse response=workUploadService.getWork(username, workId);
	         if(Objects.nonNull(response)) {
	        	 return response;
	         }
         return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
     
 }
	    
	    @PostMapping("/like")
	    public ApiResponse like(@RequestParam String username, @RequestParam String postId) {
		         ApiResponse response=workUploadService.like(Long.valueOf(postId), username);
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    
	    @DeleteMapping("/like")
	    public ApiResponse unlike(@RequestParam String username, @RequestParam String postId) {
		         ApiResponse response=workUploadService.unlike(Long.valueOf(postId), username);
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    
	    
	    @GetMapping("/like")
	    public ApiResponse getLikeList(@RequestParam String postId) {
		         ApiResponse response=workUploadService.getUserLikesByPostId(Long.valueOf(postId));
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    
	    @PostMapping("/comment")
	    public ApiResponse addComment(@RequestParam String username,@RequestParam String postId,@RequestBody CommentRequest comment) {
		         ApiResponse response=workUploadService.addComment(username, Long.valueOf(postId), comment);
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    
	    @DeleteMapping("/comment")
	    public ApiResponse deleteComment(@RequestParam String username,@RequestParam String postId,@RequestParam String commentId) {
		         ApiResponse response=workUploadService.deleteComment(username, Long.valueOf(postId), Long.valueOf(commentId));
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    
	    @PutMapping("/comment")
	    public ApiResponse addComment(@RequestParam String username,@RequestParam String postId,@RequestParam String commentId,@RequestBody CommentRequest comment) {
		         ApiResponse response=workUploadService.updateComment(username, Long.valueOf(postId),Long.valueOf(commentId), comment);
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    
	    @GetMapping("/comment")
	    public ApiResponse getComment(@RequestParam String postId) {
		         ApiResponse response=workUploadService.getCommentsByPostId(Long.valueOf(postId));
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    
	    @GetMapping("/comment/{commentId}")
	    public ApiResponse getCommentById(@PathVariable String commentId) {
		         ApiResponse response=workUploadService.getCommentByCommentId(Long.valueOf(commentId));
		         if(Objects.nonNull(response)) {
		        	 return response;
		         }
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	        
	    }
	    
}
