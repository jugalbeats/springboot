package com.example.jugalbeats.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.services.SearchService;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;
/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/user")
	public ApiResponse findUsers(HttpServletRequest request,@RequestParam(required=false)String pageIndex,
			@RequestParam(required=false)String itemPerPage ,@RequestParam(required=false)String sortOrder,
			@RequestParam(required=false)String sortBy ,@RequestParam(required=false)String searchItem
			,@RequestParam(required=false)String location ,@RequestParam(required=false)String eventDate
			,@RequestParam(required=true) boolean isArtist) {
				
				
		 PageRequest pageRequest = Utils.generatePageRequest(request);
		 if(isArtist) {
		  return searchService.findUserArtist(searchItem, location, eventDate, pageRequest);
			 
		 } 
		 return new ApiResponse(Constants.NO_CONTENT_CODE, Constants.NO_CONTENT_MESSAGE);
		
		
	}
		@GetMapping("/jobs")
	public ApiResponse findJobs(HttpServletRequest request,@RequestParam(required=false)String pageIndex,
			@RequestParam(required=false)String itemPerPage ,@RequestParam(required=false)String sortOrder,
			@RequestParam(required=false)String sortBy ,@RequestParam(required=true)String searchItem
			,@RequestParam(required=false)Long min ,@RequestParam(required=false)Long max
			) {
				
				
		 PageRequest pageRequest = Utils.generatePageRequest(request);
	     try {
		  return searchService.findJobs(searchItem, min, max, pageRequest);
	     }catch(Exception e) {
	            return new ApiResponse(Constants.NO_CONTENT_CODE, Constants.NO_CONTENT_MESSAGE);
	        }
		 }
		
		@GetMapping("/workshop")
		public ApiResponse findWorkshop(HttpServletRequest request,@RequestParam(required=false)String pageIndex,
				@RequestParam(required=false)String itemPerPage ,@RequestParam(required=false)String sortOrder,
				@RequestParam(required=false)String sortBy ,@RequestParam(required=true)String searchItem
				,@RequestParam(required=false)Long dateTime ,@RequestParam(required=false)Boolean paid,@RequestParam(required=false)String eventType
				) {
					
					
			 PageRequest pageRequest = Utils.generatePageRequest(request);
		     try {
			  return searchService.findWorkshops(paid, eventType, searchItem, dateTime, pageRequest);
		     }catch(Exception e) {
		            return new ApiResponse(Constants.NO_CONTENT_CODE, Constants.NO_CONTENT_MESSAGE);
		        }
			 }
	
		
		

}
