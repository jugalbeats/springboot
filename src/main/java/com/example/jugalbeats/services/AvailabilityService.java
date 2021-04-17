package com.example.jugalbeats.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.AvailabilityRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.models.UserAvailability;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.AvailabilityRequest;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;

@Service
public class AvailabilityService {
	  @Autowired
	  private UsersDao usersDao;
	@Autowired
	AvailabilityRepository availabilityRepository;
	
	public ApiResponse updateAvailability(AvailabilityRequest request, String username) {
		UserAvailability availability=availabilityRepository.findByUserName(username);
		if(Objects.isNull(availability)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "No data exist");
		}
		availability.setAvailabilityList(request.getAvailabilityDates().toString());
		availability.setIsAvailableAll(request.getIsAvailableAll());
		availabilityRepository.save(availability);
		
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Availability updated Successfully");	
	}

	public ApiResponse getAvailability(String username) {
		UserAvailability availability=availabilityRepository.findByUserName(username);
		if(Objects.isNull(availability)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "No data exist");
		}
		AvailabilityRequest request=new AvailabilityRequest();
		request.setAvailabilityDates(Utils.stringtoArrayOnly(availability.getAvailabilityList()));
	    request.setIsAvailableAll(availability.getIsAvailableAll());
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,request);	

	}

	public ApiResponse setAvailability(AvailabilityRequest request, String username) {
		UsersModel user=usersDao.findByUsername(username);
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Invalid Artist");
		}
UserAvailability availabilit=availabilityRepository.findByUserName(username);
		if(!Objects.isNull(availabilit)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Already exists, use put api");
		}
		UserAvailability availability =new UserAvailability();
		availability.setAvailabilityList(request.getAvailabilityDates().toString());
		availability.setIsAvailableAll(request.getIsAvailableAll());
		availability.setUsersModel(user);
		availabilityRepository.save(availability);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Availability added Successfully");	

	}

}
