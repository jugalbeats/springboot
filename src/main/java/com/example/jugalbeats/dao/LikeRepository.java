package com.example.jugalbeats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jugalbeats.models.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{

	@Query(value = "select DISTINCT u.* from likes u where liked_by = :username and post_id = :postid  ", nativeQuery = true)
	public Like findByUserNameAndPostId(@Param("username") String username,@Param("postid") long postid );
	

	@Query(value = "select DISTINCT u.* from likes u where post_id = :postid  ", nativeQuery = true)
	public List<Like> findByPostId(@Param("postid") long postid );

}
