package com.example.jugalbeats.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.config.security.Authorize;
import com.example.jugalbeats.exception.UnauthorizedException;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.CommentRequest;
import com.example.jugalbeats.pojo.WorkUploadRequest;
import com.example.jugalbeats.services.WorkUploadService;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;

/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/work")
public class WorkUploadController {

	@Autowired
	WorkUploadService workUploadService;

	@PostMapping
	@Authorize
	public ApiResponse createWork(@RequestBody WorkUploadRequest request,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), request.getUsername());
		ApiResponse response = workUploadService.addWork(request);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/{username}")
	public ApiResponse getWorks(@PathVariable("username") String username) {
		ApiResponse response = workUploadService.getWork(username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/all")
	public ApiResponse getAllWorkuploadPromotional(HttpServletRequest request,
			@RequestParam(required = false) String pageIndex, @RequestParam(required = false) String itemPerPage,
			@RequestParam(required = false) String sortOrder, @RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String searchItem) {
		PageRequest pageRequest = Utils.generatePageRequest(request);
		ApiResponse response = workUploadService.getPromotionalWork(pageRequest);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);
	}

	@PutMapping("/{username}")
	@Authorize
	public ApiResponse updateWork(@RequestBody WorkUploadRequest request, @PathVariable("username") String username,
			@RequestParam Long workId,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = workUploadService.updateWork(request, username, workId);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@DeleteMapping("/{username}")
	@Authorize
	public ApiResponse deleteWork(@PathVariable("username") String username, @RequestParam Long workId,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = workUploadService.deleteWork(username, workId);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/{username}/{workId}")
	public ApiResponse getWorkById(@PathVariable("username") String username, @PathVariable("workId") Long workId) {
		ApiResponse response = workUploadService.getWork(username, workId);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@PostMapping("/like")
	@Authorize
	public ApiResponse like(@RequestParam String username, @RequestParam String postId,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = workUploadService.like(Long.valueOf(postId), username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@DeleteMapping("/like")
	@Authorize
	public ApiResponse unlike(@RequestParam String username, @RequestParam String postId,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = workUploadService.unlike(Long.valueOf(postId), username);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/like")
	public ApiResponse getLikeList(@RequestParam String postId) {
		ApiResponse response = workUploadService.getUserLikesByPostId(Long.valueOf(postId));
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@PostMapping("/comment")
	@Authorize
	public ApiResponse addComment(@RequestParam String username, @RequestParam String postId,
			@RequestBody CommentRequest comment,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = workUploadService.addComment(username, Long.valueOf(postId), comment);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@DeleteMapping("/comment")
	@Authorize
	public ApiResponse deleteComment(@RequestParam String username, @RequestParam String postId,
			@RequestParam String commentId,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = workUploadService.deleteComment(username, Long.valueOf(postId), Long.valueOf(commentId));
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@PutMapping("/comment")
	@Authorize
	public ApiResponse addComment(@RequestParam String username, @RequestParam String postId,
			@RequestParam String commentId, @RequestBody CommentRequest comment,HttpServletRequest httpRequest ) throws UnauthorizedException {
		Utils.matchString(httpRequest.getAttribute("username").toString(), username);
		ApiResponse response = workUploadService.updateComment(username, Long.valueOf(postId), Long.valueOf(commentId),
				comment);
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/comment")
	public ApiResponse getComment(@RequestParam String postId) {
		ApiResponse response = workUploadService.getCommentsByPostId(Long.valueOf(postId));
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

	@GetMapping("/comment/{commentId}")
	public ApiResponse getCommentById(@PathVariable String commentId) {
		ApiResponse response = workUploadService.getCommentByCommentId(Long.valueOf(commentId));
		if (Objects.nonNull(response)) {
			return response;
		}
		return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

	}

}
