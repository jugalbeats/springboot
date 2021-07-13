package com.example.jugalbeats.models;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "work_upload")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkUpload extends BaseModel {


	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
	@JoinColumn(name = "user_name_work_upload", referencedColumnName = "user_name", nullable = true)
	@JsonBackReference
	private UsersModel userNameWorkUpload;

	@Column(nullable = true)
	private String imageUrl;

	@Column(nullable = true)
	private String videoUrl;

	@Column(nullable = true)
	private String location;

	@Column(nullable = true)
	private String title;

	@Column(nullable = true)
	private String caption;

	@Column(nullable = true)
	private String mediaType;

	@Column(nullable = true)
	private String content;
	@Column(nullable = true)
	private int likeCount;
	@Column(nullable = true)
	private int shareCount;
	@Column(nullable = true)
	private int commentCount;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="work_upload_id")
	private Set<Comment> comments;
	@Column(nullable = true)
	private Boolean isArchived=false;

}
