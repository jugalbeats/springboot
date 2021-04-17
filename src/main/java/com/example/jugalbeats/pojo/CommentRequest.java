package com.example.jugalbeats.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequest {
		
	private String commentContent;
	
	private String commentMediaType;
	
	@Default
	private boolean isDeleted=false;

}
