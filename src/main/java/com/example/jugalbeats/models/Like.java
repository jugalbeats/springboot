package com.example.jugalbeats.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Dhruv:2021
 * */
@Entity
@Table(name = "likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like  extends BaseModel{

	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
    @JoinColumn(name="liked_by", referencedColumnName="user_name", nullable = false)
	private UsersModel likeBy;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
    @JoinColumn(name="post_id", referencedColumnName="id", nullable = false)
	@JsonBackReference
	private WorkUpload postId;
	
	

}
