package com.example.jugalbeats.pojo;


import java.util.Set;

import com.example.jugalbeats.models.UsersModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkshopResponse {

	private long workshopId;
	private String hostname;
	private String guestname;
	private String title;
	private String eventType;
	private long dateTime;
	private String description;
	private String imageUrl;
	private Boolean paid;
 	private String meetLink;
 	private long appliedCount;
 	private Set<String> appliedUser;
}
