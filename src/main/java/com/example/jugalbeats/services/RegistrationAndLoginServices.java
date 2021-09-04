package com.example.jugalbeats.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.JwtResponse;
import com.example.jugalbeats.pojo.LoginRequest;
import com.example.jugalbeats.pojo.RegistrationForm;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.JwtTokenUtil;
import com.sun.istack.NotNull;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/*
 * dhruv:2021
 * */
@Service
public class RegistrationAndLoginServices implements UserDetailsService {

    @Autowired
    UsersDao usersDao;
    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder bcryptEncoder;

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
        model.setPassword(bcryptEncoder.encode(registrationForm.getPassword()));
        model.setArtType(registrationForm.getArtType());
        model.setCoverImage("https://drive.google.com/uc?export=view&id=1a5ernRhpYITJjd-Ua3BLInN7-N7r8r8M");
        model.setProfileImage("https://drive.google.com/uc?export=view&id=1Zg9p_7zw6kyBG8vjhcYbqk3K9hM8E7lV");
        usersDao.save(model);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "You are registered successfully");
    }
    
    public ApiResponse loginUser(LoginRequest loginRequest) {
       try {
    	UsersModel user = usersDao.findByEmail(loginRequest.getEmailOrUsername());

        if(Objects.isNull(user)) {
        	user=usersDao.findByUsername(loginRequest.getEmailOrUsername());
        }
        authenticate(user.getUsername(), loginRequest.getPassword());
		final UserDetails userDetails = loadUserByUsername(user.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, new JwtResponse(token, user.getCustomerType()));
       }
       catch(Exception e) {
           return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Email/username or password is wrong");
       }   
    }
    public ApiResponse deleteUser(String username,String password) {
        UsersModel user = usersDao.getUsersDaoByUsernameAndPassword(username, bcryptEncoder.encode(password));
      
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
	        model.setPassword(bcryptEncoder.encode(registrationForm.getPassword()));
	        if(!Objects.isNull(registrationForm.getArtType()))
		        model.setPassword(registrationForm.getArtType());
	        usersDao.save(model);
	        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "user details updated successfully");

	}
	

	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersModel user = usersDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
