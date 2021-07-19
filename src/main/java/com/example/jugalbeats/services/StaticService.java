package com.example.jugalbeats.services;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.StaticRepository;
import com.example.jugalbeats.enums.ProfessionType;
import com.example.jugalbeats.models.StaticType;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@Service
public class StaticService {

	@Autowired
	private StaticRepository staticRepository;
	
	
	public ApiResponse findProfessionList() {
	
        Map<String,String> professionMap=new LinkedHashMap<String, String>();
        for(ProfessionType option : ProfessionType.values()) {
        	professionMap.put(option.name(), option.getValue());
        }
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,professionMap);
	}

	public ApiResponse findGenreList(String profession) {
		List<StaticType> list =new ArrayList<>();
		list=staticRepository.findByProfessionType( ProfessionType.valueOf(profession).type);
		List<String>mapList=list.parallelStream().map(val->val.getGenre()).collect(Collectors.toList());
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,mapList);
	}
	
	public ApiResponse addNewGenre(String profession,String genre) {
		StaticType type=new StaticType(genre, ProfessionType.valueOf(profession).type);
		staticRepository.save(type);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,"Genre added successfully");
	}
}
