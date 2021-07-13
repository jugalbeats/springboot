package com.example.jugalbeats.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.example.jugalbeats.pojo.model.PriceInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianForm {

    String profileImage;

    String coverImage;

    String name;

    String status;

    String description;
    
    String location;

    String work;

    Boolean willingToTravel;

    String duration;

    String members;

    List<String> prefferedEvents;

    String userName;
    
    String genre;
    
    List<String> songList; 
    List<PriceInfo> priceList;
    WorkUploadRequest workUpload;
}
