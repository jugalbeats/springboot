package com.example.jugalbeats.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetApplicantResponse {

	private String username;
	private String fullname;
	private String location;
	private String profession;
	private String genre;
	private String imageUrl;
}
