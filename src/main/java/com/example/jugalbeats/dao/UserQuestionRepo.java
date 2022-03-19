package com.example.jugalbeats.dao;

import com.example.jugalbeats.models.EmbeddedUserNameQuesId;
import com.example.jugalbeats.models.UserQuestion;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserQuestionRepo extends CrudRepository<UserQuestion, EmbeddedUserNameQuesId> {

    List<UserQuestion> findByNameQuesIdUserNameArtist(String userName);

}
