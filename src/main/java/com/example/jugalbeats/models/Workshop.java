package com.example.jugalbeats.models;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Dhruv:2021
 * */
@Entity
@Table(name = "workshop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workshop extends BaseModel {
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="user_name_host_name", referencedColumnName="user_name", nullable = false)
	@JsonBackReference
	private UsersModel hostname;
	@Column(nullable = true, name = "guest_name")
	private String guestname;
	@Column(nullable = false, name = "title")
	private String title;
	@Column(nullable = false, name = "event_type")
	private String eventType;
	@Column(nullable = false, name = "date_time")
	private long dateTime;
	@Column(nullable = true, name = "description")
	private String description;
	@Column(nullable = true, name = "image_url")
	private String imageUrl;
    @Column(nullable = false, name = "paid")
	private Boolean paid;
    @Column(nullable = true, name = "meet_link")
 	private String meetLink;
//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//	@JoinColumn(name="user_name",nullable = true)
//	private Set<WorkshopApplicant> registeredUsers;
 

}
