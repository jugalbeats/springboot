package com.example.jugalbeats.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blogs extends BaseModel{
	
	@Column(nullable = false)
    private String title;
	
	@Column(nullable = true)
    private String subTitle;
	
	@Column(nullable = true)
    private String description;
	
	@Column(nullable = false)
    private String content;
	
	@Column(nullable = true)
    private String imgUrl;
	
	@Column(nullable = false)
    private String createdBy;
	
	@Column(nullable = true)
    private String updatedBy;

}
