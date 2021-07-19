package com.example.jugalbeats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jugalbeats.models.StaticType;

public interface StaticRepository extends JpaRepository<StaticType, Long>{

	
	List<StaticType> findByProfessionType(int professionType);
}
