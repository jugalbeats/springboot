package com.example.jugalbeats.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.models.PreferncesModel;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.ClientForm;
import com.example.jugalbeats.pojo.ClientResponse;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;
/*
 * dhruv:2021
 * */
@Service
public class ClientDasboardService {
	

    @Autowired
    UsersDao usersDao;
    
    public ApiResponse addData(ClientForm clientForm) {
	
	 UsersModel usersModel = usersDao.findByUsername(clientForm.getUserName());
     if(usersModel==null) {
         return new ApiResponse(Constants.FAILURE_CODE, Constants.NOT_FOUND_MESSAGE);
     }
     usersModel.setFullName(clientForm.getName());
     usersModel.setStatus(clientForm.getStatus());
     usersModel.setDescription(clientForm.getDescription());
     usersModel.setCoverImage(clientForm.getCoverImage());
     usersModel.setUsername(clientForm.getUserName());
     usersModel.setProfession(clientForm.getProfession());
     usersModel.setProfileImage(clientForm.getProfileImage());
     usersModel.setLocation(clientForm.getLocation());
     
     usersDao.save(usersModel);   
     return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
     }
    
    public ApiResponse getClientDashboard(String username) {
    	 UsersModel usersModel = usersDao.findByUsername(username);
		 if(usersModel==null) {
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.NOT_FOUND_MESSAGE);
	     }
	   
    	ClientResponse response=new ClientResponse();
    	 response.setCoverImage(usersModel.getCoverImage());
	     response.setCustomerType(usersModel.getCustomerType());
	     response.setDateOfBirth(usersModel.getDateOfBirth());
	     response.setDescription(usersModel.getDescription());
	     response.setEmail(usersModel.getEmail());
	     response.setFullName(usersModel.getFullName());
	     response.setGender(usersModel.getGender());
	     response.setIsPrivate(usersModel.getIsPrivate());
	     response.setLocation(usersModel.getLocation());
	     response.setMobile(usersModel.getMobile());
	     response.setPaidUser(usersModel.getPaidUser());
	     response.setProfession(usersModel.getProfession());
	     response.setProfileImage(usersModel.getProfileImage());
	     response.setStars(usersModel.getStars());
	     response.setStatus(usersModel.getStatus());
	     response.setSubscriptionPack(usersModel.getSubscriptionPack());
	     response.setUserName(usersModel.getUsername());
	     return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,response);    	
    	
    }
    public ApiResponse updateData(ClientResponse request) {
    	
    	UsersModel usersModel = usersDao.findByUsername(request.getUserName());
		 if(usersModel==null) {
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.NOT_FOUND_MESSAGE);
	     }
	  
	        if(!Objects.isNull(request.getCoverImage()))
	     usersModel.setCoverImage(request.getCoverImage());
	        if(!Objects.isNull(request.getCustomerType()))
	     usersModel.setCustomerType(request.getCustomerType());
	        if(!Objects.isNull(request.getDateOfBirth()))
	        usersModel.setDateOfBirth(request.getDateOfBirth());
	        if(!Objects.isNull(request.getDescription()))
	     usersModel.setDescription(request.getDescription());
	        if(!Objects.isNull(request.getEmail()))
	     usersModel.setEmail(request.getEmail());
	        if(!Objects.isNull(request.getFullName()))
	     usersModel.setFullName(request.getFullName());
	        if(!Objects.isNull(request.getGender()))
	     usersModel.setGender(request.getGender());
	        if(!Objects.isNull(request.getIsPrivate()))
	     usersModel.setIsPrivate(request.getIsPrivate());
	        if(!Objects.isNull(request.getLocation()))
	     usersModel.setLocation(request.getLocation());
	        if(!Objects.isNull(request.getMobile()))
	     usersModel.setMobile(request.getMobile());
	        if(!Objects.isNull(request.getPaidUser()))
	     usersModel.setPaidUser(request.getPaidUser());
	        if(!Objects.isNull(request.getProfession()))
	     usersModel.setProfession(request.getProfession());
	        if(!Objects.isNull(request.getProfileImage()))
	     usersModel.setProfileImage(request.getProfileImage());
	        if(!Objects.isNull(request.getStars()))
	     usersModel.setStars(request.getStars());
	        if(!Objects.isNull(request.getStatus()))
	     usersModel.setStatus(request.getStatus());
	        if(!Objects.isNull(request.getSubscriptionPack()))
	     usersModel.setSubscriptionPack(request.getSubscriptionPack());
	        if(!Objects.isNull(request.getUserName()))
	     usersModel.setUsername(request.getUserName());
        
        usersDao.save(usersModel);   
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
        }


}
