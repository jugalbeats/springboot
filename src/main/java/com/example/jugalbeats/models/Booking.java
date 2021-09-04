package com.example.jugalbeats.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {

	
	@Column(nullable = true)
	private long dateTime;

	@Column(nullable = true)
	private String location;

	@Column(nullable = true)
	private String paymentStatus;

	@Column(nullable = true)
	private String duration;

	@Column(nullable = true)
	private String caption;

	@Column(nullable = true)
	private String eventType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_name_client", referencedColumnName = "user_name", nullable = false)
	private UsersModel userNameClient;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_name_artist", referencedColumnName = "user_name", nullable = false)
	private UsersModel userNameArtist;

	private String bookingStatus;

	@Column(nullable = true)
	private String createdBy;
	

	@Column(nullable = true)
	private String updatedBy;
	
	

}