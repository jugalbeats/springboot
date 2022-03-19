package com.example.jugalbeats.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserNameQuesIdRequest {

    String userName;
    Long questionId;
    String answer;

    public UserNameQuesIdRequest() {
    }

    public UserNameQuesIdRequest(String userName, Long questionId, String answer) {
        this.userName = userName;
        this.questionId = questionId;
        this.answer = answer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
