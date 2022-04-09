package com.example.jugalbeats.dao;

import com.example.jugalbeats.models.Followers;
import com.example.jugalbeats.models.FollowersModel;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface FollowersRepo extends CrudRepository<FollowersModel, Followers> {

    @Query(value = "select * from followers where follower = ?1", nativeQuery = true)
    List<FollowersModel> getFollowersList(String username);

}
