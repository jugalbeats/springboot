package com.example.jugalbeats.services;

import java.util.*;

import com.example.jugalbeats.dao.UserQuestionRepo;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.models.EmbeddedUserNameQuesId;
import com.example.jugalbeats.models.UserQuestion;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.UserNameQuesIdRequest;
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

	@Autowired
	UsersDao usersDao;

	@Autowired
	UserQuestionRepo userQuestionRepo;

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

	public ApiResponse postQuestionWithUsername(UserNameQuesIdRequest request){
		UsersModel usersModel = usersDao.findByUsername(request.getUserName());
		Optional<Question> question = questionRepo.findById(request.getQuestionId());
		EmbeddedUserNameQuesId userNameQuesId = new EmbeddedUserNameQuesId(usersModel, question.get());
		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setNameQuesId(userNameQuesId);
		userQuestion.setAnswer(request.getAnswer());
		userQuestionRepo.save(userQuestion);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE);
	}

	public ApiResponse getQuestionAnswersByUserName(String userName){
		List<UserQuestion> userQuestions = userQuestionRepo.findByEmbeddedUserNameQuesIdUserNameArtist(userName);
		Map<String, String> quesAns = new HashMap<>();
		userQuestions.stream().forEach(question ->
				quesAns.put(question.getNameQuesId().getQuestionId().getQuestion(), question.getAnswer())
		);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, quesAns);
	}
	
}
