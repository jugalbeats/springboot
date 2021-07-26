package com.example.jugalbeats.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FAQsResponse {

	String username;
	String question;
	String answer;
	private Date modified;
	private Date created;
	long quesId;
	long faqId;
}
