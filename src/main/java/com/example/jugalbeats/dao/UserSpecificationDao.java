package com.example.jugalbeats.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.jugalbeats.models.UserSpecification;


public interface UserSpecificationDao  extends CrudRepository<UserSpecification, Long> {
	
	@Query(value = "select DISTINCT u.* from  user_specification u where user_name_specification = :username  ",nativeQuery = true)
    public UserSpecification findByUserName(String username);


}
