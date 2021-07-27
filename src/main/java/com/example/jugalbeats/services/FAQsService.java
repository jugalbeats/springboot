package com.example.jugalbeats.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.FAQsRepository;
import com.example.jugalbeats.dao.QuestionRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.models.FAQs;
import com.example.jugalbeats.models.Question;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.FAQsRequest;
import com.example.jugalbeats.pojo.FAQsResponse;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@Service
public class FAQsService {

	@Autowired
	FAQsRepository faqsRepository;
	@Autowired
	QuestionRepository questionRepo;
	
	@Autowired
	UsersDao usersDao;
	
	public ApiResponse addFAQs(FAQsRequest request) {
		UsersModel user=usersDao.findByUsername(request.getUsername());
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "FAQs post unsuccessful");
		}
		
		if(Objects.isNull(faqsRepository.findFaqByUsernameAndQuesId(request.getUsername(), request.getQuesId()))) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "FAQs already exist unsuccessful");
		}
		FAQs faq=new FAQs();
		faq.setAnswer(request.getAnswer());
		faq.setQuesId(request.getQuesId());
		faq.setUserNameFaqs(user);
		faqsRepository.save(faq);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "FAQs has been posted successfully");	
	}
	
	public ApiResponse getFAQs(String userName) {
		
	    List<FAQs>workList= new ArrayList<>();
	    workList=faqsRepository.findByUserName(userName);
	    List<FAQsResponse> response= new ArrayList<>();
	    Map<Long,String>qMap=new ConcurrentHashMap<>(questionRepo.findAll().parallelStream().collect(Collectors.toMap(Question :: getId, Question ::  getQuestion)));
	    workList.parallelStream().forEach(work->{
	    	response.add(FAQsResponse.builder().answer(work.getAnswer()).created(work.getCreated()).modified(work.getModified())
	    			.question(qMap.get(work.getQuesId())).username(work.getUserNameFaqs().getUsername()).faqId(work.getId()).quesId(work.getQuesId()).build());
	    	
	    });
	    
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,response);
		
	}

	public ApiResponse deleteFAQsById(String username, long id) {
		FAQs post=faqsRepository.findFaqByUsernameAndFaqId(username, id);
		if(Objects.isNull(post)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "no data found");
		}
		faqsRepository.deleteById(id);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,"Faq deleted successfully");
	
	}

	public ApiResponse updateFAQs(FAQsRequest request) {
		// TODO Auto-generated method stub
		FAQs post=faqsRepository.findFaqByUsernameAndQuesId(request.getUsername(), request.getQuesId());
		if(Objects.isNull(post)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "no data found");
		}
		post.setAnswer(request.getAnswer());
		faqsRepository.save(post);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,"Faq updated successfully");
	}
}
