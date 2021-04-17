package com.example.jugalbeats.pojo;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkUploadRequest {

    private String imageUrl ;

    private String videoUrl;

    private String location;
	
    private String title;
	
    private String caption;
    
    private String username;
    
	private String content;

    
    private String mediaType;

}
