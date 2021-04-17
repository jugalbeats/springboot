package com.example.jugalbeats.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.FAQsRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.models.FAQs;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.FAQsRequest;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@Service
public class FAQsService {

	@Autowired
	FAQsRepository faqsRepository;
	
	
	@Autowired
	UsersDao usersDao;
	
	public ApiResponse addFAQs(FAQsRequest request) {
		UsersModel user=usersDao.findByUsername(request.getUsername());
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "FAQs post unsuccessful");
		}
		FAQs faq=new FAQs();
		faq.setAnswer(request.getAnswer());
		faq.setQuesId(request.getQuesId());
		faq.setUserNameFaqs(user);
		faqsRepository.save(faq);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "FAQs has been posted successfully");	
	}
	
	public ApiResponse getFAQs(String userName) {
		
	    List<FAQs>workList=faqsRepository.findByUserName(userName);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,workList);
		
	}
}
