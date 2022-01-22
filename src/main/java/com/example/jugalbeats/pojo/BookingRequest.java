package com.example.jugalbeats.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

	private long dateTime;

	private String location;

	private String paymentStatus;

	private String duration;

	private String caption;

	private String eventType;
	
	private String usernameClient;

	private String usernameArtist;
	
	private Boolean isDeleted;
	
	private int bookingStatus;

	@Override
	public String toString() {
		return "BookingRequest{" +
				"dateTime=" + dateTime +
				", location='" + location + '\'' +
				", paymentStatus='" + paymentStatus + '\'' +
				", duration='" + duration + '\'' +
				", caption='" + caption + '\'' +
				", eventType='" + eventType + '\'' +
				", usernameClient='" + usernameClient + '\'' +
				", usernameArtist='" + usernameArtist + '\'' +
				", isDeleted=" + isDeleted +
				", bookingStatus=" + bookingStatus +
				'}';
	}
}
