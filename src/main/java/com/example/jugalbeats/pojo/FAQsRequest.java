package com.example.jugalbeats.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FAQsRequest {
	
	String username;
	Long quesId;
	String answer;

}
