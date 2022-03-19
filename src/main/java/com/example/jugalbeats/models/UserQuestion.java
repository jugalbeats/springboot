package com.example.jugalbeats.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuestion implements Serializable {

    @EmbeddedId
    private EmbeddedUserNameQuesId nameQuesId;
    private String answer;
}
