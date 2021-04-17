package com.example.jugalbeats.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

	 String profileImage;

	    String coverImage;

	    String status;

	    String description;
	    
	    String userName;
	    
	    String location;
	    
	    String profession;
	    
	    private String email;

	    private String customerType;

	    private String mobile;

	    private String dateOfBirth;

	    private String fullName;

	    private Boolean paidUser;

	    private String subscriptionPack;
	    
	    private Boolean isPrivate;

	    private String gender;

	    private Double stars;

}
