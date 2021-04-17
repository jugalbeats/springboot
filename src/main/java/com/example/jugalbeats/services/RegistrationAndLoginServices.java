package com.example.jugalbeats.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.LoginRequest;
import com.example.jugalbeats.pojo.RegistrationForm;
import com.example.jugalbeats.utils.Constants;
import com.sun.istack.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/*
 * dhruv:2021
 * */
@Service
public class RegistrationAndLoginServices {

    @Autowired
    UsersDao usersDao;

    public ApiResponse registrationUser(RegistrationForm registrationForm) {
        UsersModel model = usersDao.findByEmail(registrationForm.getEmail());
        if(model!=null)
            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "This e-mail is already present. Please login");
        model = new UsersModel();
        model.setUsername(registrationForm.getUsername().toLowerCase());
        model.setMobile(registrationForm.getMobile());
        model.setProfession(registrationForm.getProfession().toLowerCase());
        model.setEmail(registrationForm.getEmail());
        model.setFullName(registrationForm.getFirstName() + " " + registrationForm.getLastName());
        model.setCustomerType(registrationForm.getUserType().toLowerCase());
        model.setGender(registrationForm.getGender());
        model.setEmail(registrationForm.getEmail());
        model.setPassword(registrationForm.getPassword());
        model.setArtType(registrationForm.getArtType());
        usersDao.save(model);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "You are registered successfully");
    }
    
    public ApiResponse loginUser(LoginRequest loginRequest) {
        UsersModel user = usersDao.getUsersDaoByEmailAndPassword(loginRequest.getEmailOrUsername(),loginRequest.getPassword());
        if(Objects.isNull(user)) {
        	user=usersDao.getUsersDaoByUsernameAndPassword(loginRequest.getEmailOrUsername(),loginRequest.getPassword());
        }
        if(!Objects.isNull(user)) {
            return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, user);
        }
            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Email/username or password is wrong");
        
       
    }
    public ApiResponse deleteUser(String username,String password) {
        UsersModel user = usersDao.getUsersDaoByUsernameAndPassword(username, password);
      
        if(!Objects.isNull(user)) {
        	usersDao.delete(user);
            return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Deleted user successfully");
        }
            return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Email/username or password is wrong");
        
       
    }

	public ApiResponse updateUser(RegistrationForm registrationForm, String username) {
		// TODO Auto-generated method stub
		 UsersModel model = usersDao.findByUsername(username);
	        if(Objects.isNull(model))
	        {  return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "user does not exists");}
	        
	        if(!Objects.isNull(registrationForm.getMobile()))
	        model.setMobile(registrationForm.getMobile());
	        if(!Objects.isNull(registrationForm.getProfession()))
	        model.setProfession(registrationForm.getProfession().toLowerCase());
	        if(!Objects.isNull(registrationForm.getEmail()))
	        model.setEmail(registrationForm.getEmail());
	        if(!Objects.isNull(registrationForm.getFirstName()) && !Objects.isNull(registrationForm.getLastName()))
	        model.setFullName(registrationForm.getFirstName() + " " + registrationForm.getLastName());
	        if(!Objects.isNull(registrationForm.getGender()))
	        model.setGender(registrationForm.getGender());
	        if(!Objects.isNull(registrationForm.getEmail()))
	        model.setEmail(registrationForm.getEmail());
	        if(!Objects.isNull(registrationForm.getPassword()))
	        model.setPassword(registrationForm.getPassword());
	        if(!Objects.isNull(registrationForm.getArtType()))
		        model.setPassword(registrationForm.getArtType());
	        usersDao.save(model);
	        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "user details updated successfully");

	}

}
