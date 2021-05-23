package com.example.jugalbeats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jugalbeats.models.Workshop;


public interface WorkshopRepository extends JpaRepository<Workshop, Long>{

	@Query(value = "select DISTINCT w.* from  workshop w where user_name_host_name = :username  and id =:id ",nativeQuery = true)
    public Workshop findByUserNameAndId(@Param("username")String username,@Param("id")Long id);
	
	@Query(value = "select DISTINCT w.* from  workshop w where user_name_host_name = :username ",nativeQuery = true)
	public List<Workshop> findByHostname(@Param("username")String username);
}
