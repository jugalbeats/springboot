package com.example.jugalbeats.dao;

import com.example.jugalbeats.models.UserQuestion;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserQuestionRepo extends CrudRepository<UserQuestion, Long> {

    List<UserQuestion> findByEmbeddedUserNameQuesIdUserNameArtist(String userName);

}
