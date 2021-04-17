package com.example.jugalbeats.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jugalbeats.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
