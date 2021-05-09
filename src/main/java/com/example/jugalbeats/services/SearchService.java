package com.example.jugalbeats.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.JobPostRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.enums.UserType;
import com.example.jugalbeats.models.JobPost;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.utils.Constants;

import io.micrometer.core.instrument.util.StringUtils;
/*
 * dhruv:2021
 * */
@Service
public class SearchService {

	@Autowired
	private UsersDao userDao;
	
	@Autowired
	private JobPostRepository jobPostRepo;
	
	public ApiResponse findUserArtist(String searchStr,String location,String eventDate,PageRequest pageRequest) {
		
		Page<UsersModel>usersPage=null;
		
		   if(!StringUtils.isBlank(location) && !StringUtils.isBlank(searchStr) && !StringUtils.isBlank(eventDate) ) {
			   usersPage=userDao.findByCustomerTypeAndAvailability(eventDate, UserType.ARTIST.getValue(), searchStr, location, pageRequest);
					   }
		   else if (!StringUtils.isBlank(location) && !StringUtils.isBlank(searchStr))
		   {
			   usersPage=userDao.findByLocationAndCustomerTypeAndProfession(location, UserType.ARTIST.getValue(), searchStr, pageRequest);
		   }
		   else if (!StringUtils.isBlank(searchStr) && !StringUtils.isBlank(eventDate))
		   {
			   usersPage=userDao.findByCustomerTypeAndAvailability(eventDate, UserType.ARTIST.getValue(), searchStr, pageRequest);
					   }
		   else if (!StringUtils.isBlank(location) && !StringUtils.isBlank(eventDate))
		   {
			   usersPage=userDao.findByCustomerTypeAndAvailabilityAndLocation(eventDate, UserType.ARTIST.getValue(), location, pageRequest);
		   }
		   else if (!StringUtils.isBlank(searchStr))
		   {
			   usersPage=userDao.findByProfessionAndCustomerType(searchStr,UserType.ARTIST.getValue(),pageRequest);
					   }
		   else if (!StringUtils.isBlank(location))
		   {
			   usersPage=userDao.findByLocationAndCustomerType(location,UserType.ARTIST.getValue(),pageRequest);
					   }
		   else if (!StringUtils.isBlank(eventDate))
		   {
			   usersPage=userDao.findByCustomerTypeAndAvailability(eventDate, UserType.ARTIST.getValue(), pageRequest);
					   }
		   else {
			usersPage=userDao.findByCustomerType(UserType.ARTIST.getValue(),pageRequest);
		   }

//		usersList.stream().forEach(user->
//		{ 
//		   if(!StringUtils.isBlank(searchStr) && !StringUtils.isBlank(user.getProfession()))
//			{if(Pattern.compile(Pattern.quote(searchStr), Pattern.CASE_INSENSITIVE).matcher(user.getProfession()).find()) {
//				filteredList.add(user);
//			}}
//		   if(!StringUtils.isBlank(location) && !StringUtils.isBlank(user.getLocation()))
//				{if(Pattern.compile(Pattern.quote(location), Pattern.CASE_INSENSITIVE).matcher(user.getLocation()).find()) {
//					filteredList.add(user);
//				}}
//		}
//		);
//		List<UsersModel>finalList=null;
//		if(!filteredList.isEmpty() && Objects.nonNull(filteredList)) 
//			{finalList=filteredList.stream().distinct().collect(Collectors.toList());}
//		else finalList=usersPage.getContent();
//		Page<UsersModel>page= new PageImpl<UsersModel>(finalList,pageRequest,finalList.size());
		
		
		
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,usersPage);

	}
  public ApiResponse findJobs(String searchStr,Long min,Long max, PageRequest pageRequest) {
	  Page<JobPost> page=null; 
	 if(Objects.nonNull(max))
	  page=jobPostRepo.findJobPostByTitle(searchStr, min, max, pageRequest);
	 else page=jobPostRepo.findJobPostByTitle(searchStr, pageRequest);

	return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,page);

  }	
	
}
