package com.example.jugalbeats.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.QuestionRepository;
import com.example.jugalbeats.models.Question;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.utils.Constants;


@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepo;
	
	public ApiResponse postQuestion(Question question) {
		
		questionRepo.save(question);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Question added successfully");	
	}
	
	public ApiResponse getAllQuestion() {
		
	    List<Question>quesList=questionRepo.findAll();
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,quesList);
		
	}

	public ApiResponse deleteQues(Long quesId) {
		questionRepo.deleteById(quesId);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Question deleted successfully");	
	}
	
}
