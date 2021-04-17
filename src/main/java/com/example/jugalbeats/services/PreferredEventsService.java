package com.example.jugalbeats.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.PreferredEventRepository;
import com.example.jugalbeats.models.PreferredEvents;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.utils.Constants;

@Service
public class PreferredEventsService {

	@Autowired
	PreferredEventRepository preferEventRepo;
	
	public ApiResponse postPreferredEvent(PreferredEvents events) {
		
		preferEventRepo.save(events);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Your question added successfully");	
	}
	
	public ApiResponse getPreferredEvent() {
		
	    List<PreferredEvents>eventList=preferEventRepo.findAll();
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,eventList);	
	}
	
	public List<PreferredEvents> getPreferredEvents(List<Long> ids){
		List<PreferredEvents>eventList=null;
		if(ids.isEmpty()) {
			return eventList;
		}
		eventList=preferEventRepo.findAllById(ids);
		 return eventList;
		
		
	}
}
