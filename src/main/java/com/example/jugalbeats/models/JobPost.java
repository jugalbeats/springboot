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

@Entity
@Table(name = "job_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPost extends BaseModel{
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="user_name_job_post", referencedColumnName="user_name", nullable = true)
	@JsonBackReference
	private UsersModel userNameJobPost;

	@Column(nullable = true)
    private String duration ;

	@Column(nullable = true)
    private long payment;

	@Column(nullable = true)
    private String location;

	@Column(nullable = true)
    private String landmark;

	@Column(nullable = true)
    private String occasion;
	
	@Column(nullable = true)
    private String specialAmenities;
	
	@Column(nullable = true)
    private String requirement;
	
	@Column(nullable = false)
    private String description;
	
	@Column(nullable = false)
    private String title;
	
	

}
