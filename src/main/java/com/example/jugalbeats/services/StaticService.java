package com.example.jugalbeats.services;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.jugalbeats.enums.ProfessionType;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.utils.Constants;

/*
 * dhruv:2021
 * */
@Service
public class StaticService {

	
	public ApiResponse findProfessionList() {
	
        Map<String,String> professionMap=new LinkedHashMap<String, String>();
        for(ProfessionType option : ProfessionType.values()) {
        	professionMap.put(option.name(), option.getValue());
        }
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,professionMap);
	}
}
