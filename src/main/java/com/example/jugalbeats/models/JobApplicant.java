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
@Table(name = "job_applicant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicant extends BaseModel{
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
    @JoinColumn(name="apply_by", referencedColumnName="user_name", nullable = false)
	private UsersModel applyBy;
	
	@Column(nullable = true)
	private String status;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
    @JoinColumn(name="job_post_id", referencedColumnName="id", nullable = false)
	@JsonBackReference
	private JobPost postId;
	
	

}
