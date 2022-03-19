package com.example.jugalbeats.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class EmbeddedUserNameQuesId implements Serializable {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_name_artist", referencedColumnName = "user_name", nullable = false)
    private UsersModel userNameArtist;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "questionId", referencedColumnName = "id", nullable = false)
    private Question questionId;

    public EmbeddedUserNameQuesId() {
    }

    public EmbeddedUserNameQuesId(UsersModel userNameArtist, Question questionId) {
        this.userNameArtist = userNameArtist;
        this.questionId = questionId;
    }

    public UsersModel getUserNameArtist() {
        return userNameArtist;
    }

    public void setUserNameArtist(UsersModel userNameArtist) {
        this.userNameArtist = userNameArtist;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }
}
