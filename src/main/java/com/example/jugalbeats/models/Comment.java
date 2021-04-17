package com.example.jugalbeats.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Dhruv:2021
 * */
@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment  extends BaseModel{
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
    @JoinColumn(name="comment_by", referencedColumnName="user_name", nullable = false)
	private UsersModel commentBy;
	
	@Column(nullable = true)
	private String commentContent;
	
	@Column(nullable = true)
	private String commentMediaType;
	
}
