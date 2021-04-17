package com.example.jugalbeats.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jugalbeats.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
