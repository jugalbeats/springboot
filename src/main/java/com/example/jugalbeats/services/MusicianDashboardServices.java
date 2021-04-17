package com.example.jugalbeats.services;

import com.example.jugalbeats.dao.PreferencesDao;
import com.example.jugalbeats.dao.UserSpecificationDao;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.dao.WorkUploadRepository;
import com.example.jugalbeats.models.PreferncesModel;
import com.example.jugalbeats.models.UserSpecification;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.models.WorkUpload;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.MusicianForm;
import com.example.jugalbeats.pojo.MusicianResponse;
import com.example.jugalbeats.pojo.model.PriceInfo;
import com.example.jugalbeats.pojo.model.UserSongs;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/*
 * dhruv:2021
 * */
@Service
public class MusicianDashboardServices {

	@Autowired
	PreferencesDao preferencesDao;

	@Autowired
	UsersDao usersDao;

	@Autowired
	UserSpecificationDao userSpecificationDao;
	
	@Autowired
 	WorkUploadRepository workRepository;
	
	@Autowired
	PreferredEventsService preferedEventService;

	public ApiResponse addData(MusicianForm musicianForm){
        UsersModel usersModel = usersDao.findByUsername(musicianForm.getUserName());
        if(usersModel==null) {
            return new ApiResponse(Constants.FAILURE_CODE, Constants.NOT_FOUND_MESSAGE);
        }
        PreferncesModel preferncesModel = preferencesDao.findByUsersModel(usersModel);
        if(preferncesModel==null){
            preferncesModel = new PreferncesModel();
        }
        UserSpecification userSpecification = userSpecificationDao.findByUserName(musicianForm.getUserName());
        		 if(userSpecification==null){
        			 userSpecification = new UserSpecification();
        	        }
        if(!Objects.isNull(musicianForm.getWorkUpload())){
        	WorkUpload newWork=new WorkUpload();
    		newWork.setTitle(musicianForm.getWorkUpload().getTitle());
    		newWork.setLocation(musicianForm.getWorkUpload().getLocation());
    		newWork.setImageUrl(musicianForm.getWorkUpload().getVideoUrl());
    		newWork.setCaption(musicianForm.getWorkUpload().getCaption());
    		newWork.setUserNameWorkUpload(usersModel);
    		workRepository.save(newWork);
        	
        }
        preferncesModel.setMembers(Utils.getStringValue(musicianForm.getMembers()));
        preferncesModel.setPerformanceDuration(Utils.getStringValue(musicianForm.getDuration()));
        preferncesModel.setWillingToTravel(Utils.getBooleanValue(musicianForm.getWillingToTravel()));
        preferncesModel.setPrefferedEvents(Utils.getStringValue(musicianForm.getPrefferedEvents()));
        usersModel.setFullName(musicianForm.getName());
        usersModel.setStatus(musicianForm.getStatus());
        usersModel.setDescription(musicianForm.getDescription());
        usersModel.setCoverImage(musicianForm.getCoverImage());
        usersModel.setUsername(musicianForm.getUserName());
        usersModel.setProfileImage(musicianForm.getProfileImage());
        usersModel.setGenre(musicianForm.getGenre());
        usersModel.setLocation(musicianForm.getLocation());
        userSpecification.setPriceList(Utils.getStringValue(musicianForm.getPriceList()));
        userSpecification.setSongList(Utils.getStringValue(musicianForm.getSongList()));
        
        
        preferncesModel.setUsersModel(usersModel);
        userSpecification.setUsersModel(usersModel);
        
        usersDao.save(usersModel);
        userSpecificationDao.save(userSpecification);
        preferencesDao.save(preferncesModel);
        
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
    }

	public ApiResponse getMusiciandata(String username) {
		UsersModel usersModel = usersDao.findByUsername(username);
		if (usersModel == null) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.NOT_FOUND_MESSAGE);
		}
		PreferncesModel preferncesModel = preferencesDao.findByUsersModel(usersModel);
        UserSpecification userSpecification = userSpecificationDao.findByUserName(username);
	    List<WorkUpload>workList=workRepository.findByUserName(username);


		MusicianResponse response = new MusicianResponse();
		response.setCoverImage(usersModel.getCoverImage());
		response.setCustomerType(usersModel.getCustomerType());
		response.setDateOfBirth(usersModel.getDateOfBirth());
		response.setDescription(usersModel.getDescription());
		if(!Objects.isNull(preferncesModel)) {
		response.setDuration(preferncesModel.getPerformanceDuration());
		response.setWillingToTravel(preferncesModel.getWillingToTravel());
		response.setPrefferedEvents(preferedEventService.getPreferredEvents(preferncesModel.getPrefferedEvents()));
		response.setWillingToTravel(preferncesModel.getWillingToTravel());
		response.setMembers(preferncesModel.getMembers());
		}
		response.setEmail(usersModel.getEmail());
		response.setFullName(usersModel.getFullName());
		response.setGender(usersModel.getGender());
		response.setIsPrivate(usersModel.getIsPrivate());
		response.setLocation(usersModel.getLocation());
		response.setMobile(usersModel.getMobile());
		response.setPaidUser(usersModel.getPaidUser());
		response.setProfession(usersModel.getProfession());
		response.setProfileImage(usersModel.getProfileImage());
		response.setProfileVideo(usersModel.getProfileVideo());
		response.setStars(usersModel.getStars());
		response.setStatus(usersModel.getStatus());
		response.setSubscriptionPack(usersModel.getSubscriptionPack());
		response.setUserName(usersModel.getUsername());
		response.setGenre(usersModel.getGenre());
		if(!Objects.isNull(userSpecification)) {
		response.setPriceList(userSpecification.getPriceList());
		response.setSongList(userSpecification.getSongList());
		}
		if(!workList.isEmpty()) {
		response.setWorkUpload(workList);
		}
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, response);

	}
	
	public ApiResponse updateData(MusicianForm musicianForm, String username){
        UsersModel usersModel = usersDao.findByUsername(username);
        if(usersModel==null) {
            return new ApiResponse(Constants.FAILURE_CODE, Constants.NOT_FOUND_MESSAGE);
        }
        PreferncesModel preferncesModel = preferencesDao.findByUsersModel(usersModel);
        if(preferncesModel==null){
            preferncesModel = new PreferncesModel();
        }
        UserSpecification userSpecification = userSpecificationDao.findByUserName(username);
        		 if(userSpecification==null){
        			 userSpecification = new UserSpecification();
        	        }
//        if(!Objects.isNull(musicianForm.getWorkUpload())){
//        	WorkUpload newWork=new WorkUpload();
//    		newWork.setTitle(musicianForm.getWorkUpload().getTitle());
//    		newWork.setLocation(musicianForm.getWorkUpload().getLocation());
//    		newWork.setImageUrl(musicianForm.getWorkUpload().getVideoUrl());
//    		newWork.setCaption(musicianForm.getWorkUpload().getCaption());
//    		newWork.setUserNameWorkUpload(usersModel);
//    		workRepository.save(newWork);
//        	
//        }
         userSpecification.setUsersModel(usersModel);
         preferncesModel.setUsersModel(usersModel);


        if(!Objects.isNull(musicianForm.getMembers()))
        preferncesModel.setMembers(Utils.getStringValue(musicianForm.getMembers()));
        if(!Objects.isNull(musicianForm.getDuration()))
        preferncesModel.setPerformanceDuration(Utils.getStringValue(musicianForm.getDuration()));
        if(!Objects.isNull(musicianForm.getWillingToTravel()))
        preferncesModel.setWillingToTravel(Utils.getBooleanValue(musicianForm.getWillingToTravel()));
        if(!Objects.isNull(musicianForm.getPrefferedEvents()))
        preferncesModel.setPrefferedEvents(Utils.getStringValue(musicianForm.getPrefferedEvents()));
        if(!Objects.isNull(musicianForm.getName()))
        usersModel.setFullName(musicianForm.getName());
        if(!Objects.isNull(musicianForm.getStatus()))
        usersModel.setStatus(musicianForm.getStatus());
        if(!Objects.isNull(musicianForm.getDescription()))
        usersModel.setDescription(musicianForm.getDescription());
        if(!Objects.isNull(musicianForm.getCoverImage()))
        usersModel.setCoverImage(musicianForm.getCoverImage());
        if(!Objects.isNull(musicianForm.getUserName()))
        usersModel.setUsername(musicianForm.getUserName());
        if(!Objects.isNull(musicianForm.getProfileImage()))
        usersModel.setProfileImage(musicianForm.getProfileImage());
        if(!Objects.isNull(musicianForm.getGenre()))
        usersModel.setGenre(musicianForm.getGenre());
        if(!Objects.isNull(musicianForm.getPriceList()))
        userSpecification.setPriceList(Utils.getStringValue(musicianForm.getPriceList()));
        if(!Objects.isNull(musicianForm.getSongList()))
        userSpecification.setSongList(Utils.getStringValue(musicianForm.getSongList()));
        if(!Objects.isNull(musicianForm.getLocation()))
        usersModel.setLocation(musicianForm.getLocation());
        

        usersDao.save(usersModel);
        userSpecificationDao.save(userSpecification);
        preferencesDao.save(preferncesModel);
        
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
    }

	public ApiResponse deletePrice(PriceInfo price, String username) {
		// TODO Auto-generated method stub
		
		UserSpecification spec= userSpecificationDao.findByUserName(username);
		 if(Objects.isNull(spec)) {
	            return new ApiResponse(Constants.FAILURE_CODE, Constants.NOT_FOUND_MESSAGE,"Does not exists");
	        }
		List<PriceInfo>prices=spec.getPriceList();
		prices.remove(price);
		Gson gson =new Gson();
		
		spec.setPriceList(prices==null?null:gson.toJson(prices));
		userSpecificationDao.save(spec);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,"deleted successfully");

		
	}

}
