package com.example.jugalbeats.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.jugalbeats.models.UserAvailability;


public interface AvailabilityRepository extends CrudRepository<UserAvailability, Long>{

	@Query(value = "select DISTINCT u.* from  user_availability u where username_available = :username  ", nativeQuery = true)
	public UserAvailability findByUserName(String username);
}
