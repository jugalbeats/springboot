package com.example.jugalbeats.pojo;

import javax.persistence.Column;

import lombok.Data;
/*
 * dhruv:2021
 * */
@Data
public class WorkshopRequest {

	private String guestname;
	private String title;
	private String eventType;
	private long dateTime;
	private String description;
	private String imageUrl;
	private Boolean paid;
 	private String meetLink;
}
