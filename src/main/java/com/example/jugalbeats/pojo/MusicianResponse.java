package com.example.jugalbeats.pojo;

import java.util.List;

import com.example.jugalbeats.models.PreferredEvents;
import com.example.jugalbeats.models.WorkUpload;
import com.example.jugalbeats.pojo.model.PriceInfo;
import com.example.jugalbeats.pojo.model.UserSongs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianResponse {
	
	String profileImage;

    String coverImage;

    String status;

    String description;

    String work;

    Boolean willingToTravel;

    String duration;

    String members;

    List<PreferredEvents> prefferedEvents;

    String userName;

    private String email;

    private String customerType;

    private String mobile;

    private String dateOfBirth;

    private String fullName;

    private Boolean paidUser;

    private String subscriptionPack;
    private String profileVideo;

    private Boolean isPrivate;

    private String location;

    private String gender;

    private Double stars;

    private String profession;
    
    private String genre;
    
    private List<UserSongs> songList; 
    private List<PriceInfo> priceList;
    List<WorkUpload> workUpload;

 
}
