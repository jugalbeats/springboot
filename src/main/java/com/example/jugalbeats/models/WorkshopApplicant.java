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
@Table(name = "workshop_applicant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopApplicant extends BaseModel{
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
    @JoinColumn(name="apply_by", referencedColumnName="user_name", nullable = false)
	@JsonBackReference
	private UsersModel applyBy;
	
	@Column(nullable = true)
	private String status;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
    @JoinColumn(name="workshop_id", referencedColumnName="id", nullable = false)
	@JsonBackReference
	private Workshop postId;

}
