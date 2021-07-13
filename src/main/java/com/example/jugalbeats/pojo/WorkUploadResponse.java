package com.example.jugalbeats.pojo;

import java.util.Date;
import java.util.Set;

import com.example.jugalbeats.models.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkUploadResponse {
	private String username;
	private String imageUrl;
	private String videoUrl;
	private String location;
	private String title;
	private String caption;
	private String mediaType;
	private String content;
	private int likeCount;
	private int shareCount;
	private int commentCount;
	private Set<Comment> comments;
	private Boolean isArchived;
	private long id;
	private Date created;
	private Date modified;
	private Boolean isDeleted;
}
