package com.example.jugalbeats.pojo;

import javax.persistence.Column;

import com.example.jugalbeats.models.UsersModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostRequest {
	
	private String username;

    private String duration ;

    private long payment;

    private String location;

    private String landmark;

    private String occasion;
	
    private String specialAmenities;
	
    private String requirement;
	
    private String description;
	
    private String title;

	
}
