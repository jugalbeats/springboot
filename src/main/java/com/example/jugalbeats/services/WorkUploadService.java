package com.example.jugalbeats.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.CommentRepository;
import com.example.jugalbeats.dao.LikeRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.dao.WorkUploadRepository;
import com.example.jugalbeats.models.Comment;
import com.example.jugalbeats.models.Like;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.models.WorkUpload;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.CommentRequest;
import com.example.jugalbeats.pojo.WorkUploadRequest;
import com.example.jugalbeats.utils.Constants;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/*
 * dhruv:2021
 * */
@Service
public class WorkUploadService {
	
	@Autowired
	WorkUploadRepository workRepository;
	
	@Autowired
	LikeRepository likeRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UsersDao usersDao;

	public ApiResponse addWork(WorkUploadRequest request) {
		UsersModel user=usersDao.findByUsername(request.getUsername());
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Work add unsuccessful");
		}
		WorkUpload newWork=new WorkUpload();
		newWork.setTitle(request.getTitle());
		newWork.setLocation(request.getLocation());
		newWork.setImageUrl(request.getImageUrl());
		newWork.setVideoUrl(request.getVideoUrl());
		newWork.setCaption(request.getCaption());
		newWork.setUserNameWorkUpload(user);
		newWork.setMediaType(request.getMediaType());
		newWork.setContent(request.getContent());
		workRepository.save(newWork);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "You work has been posted successfully");	
	}
	
	public ApiResponse getWork(String userName) {
		
	    List<WorkUpload>workList=workRepository.findByUserName(userName);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,workList);
		
	}
	
	public ApiResponse updateWork(WorkUploadRequest request,String username,Long id ) {
		UsersModel user=usersDao.findByUsername(username);
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "given username does not exist");
		}
		Optional<WorkUpload> work=workRepository.findById(id);
		if(!work.isPresent()) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Work with given id doesnot exist ");
		}
        if(!Objects.isNull(request.getTitle()))
		work.get().setTitle(request.getTitle());
        if(!Objects.isNull(request.getLocation()))
		work.get().setLocation(request.getLocation());   
        if(!Objects.isNull(request.getImageUrl()))	
		work.get().setImageUrl(request.getImageUrl());
        if(!Objects.isNull(request.getVideoUrl()))
		work.get().setVideoUrl(request.getVideoUrl());
        if(!Objects.isNull(request.getCaption()))
		work.get().setCaption(request.getCaption());         
        if(!Objects.isNull(request.getMediaType()))
    		work.get().setCaption(request.getMediaType());
        if(!Objects.isNull(request.getContent())) {
        	work.get().setContent(request.getContent());
        }



		workRepository.save(work.get());
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "You work has been posted successfully");	
	}
	@Transactional
	public ApiResponse like(Long postId, String username) {
		Optional<WorkUpload> work=workRepository.findById(postId);
		if(!work.isPresent()) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Work with given id doesnot exist ");
		}
		UsersModel user=usersDao.findByUsername(username);
		Like lik=new Like();
		lik.setLikeBy(user);
		lik.setPostId(work.get());
		work.get().setLikeCount(work.get().getLikeCount()+1);
		workRepository.save(work.get());
		likeRepository.save(lik);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Post Liked");	
	}
	@Transactional
	public ApiResponse unlike(Long postId, String username) {
		Optional<WorkUpload> work=workRepository.findById(postId);
		if(!work.isPresent()) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Work with given id doesnot exist ");
		}
		Like lik=likeRepository.findByUserNameAndPostId(username, postId);
		if(Objects.isNull(lik)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, " given id's doesnot exist ");
			
		}
		work.get().setLikeCount(work.get().getLikeCount()-1);
		workRepository.save(work.get());
		likeRepository.delete(lik);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Post UnLiked");	
	}
	
	public ApiResponse addComment(String username,  Long postId, CommentRequest comment) {
		Optional<WorkUpload> work=workRepository.findById(postId);
		if(!work.isPresent()) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Work with given id doesnot exist ");
		}
		UsersModel commentBy=usersDao.findByUsername(username);
		Comment comm=new Comment();
		comm.setCommentBy(commentBy);
		comm.setCommentContent(comment.getCommentContent());
		comm.setCommentMediaType(comment.getCommentMediaType());
		Set<Comment>listComment=work.get().getComments();
		listComment.add(comm);
		work.get().setComments(listComment);
		work.get().setCommentCount(listComment.size());
		workRepository.save(work.get());
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Comment Posted");	
		
	}
	
	public ApiResponse getUserLikesByPostId(Long postId) {
		List<Like>likes=likeRepository.findByPostId(postId);
		List<String>list=new ArrayList<>();
				likes.stream().forEach(li->{
					list.add(li.getLikeBy().getUsername());
			
		});
     return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,list);			
	}
	
	public ApiResponse getCommentsByPostId(Long postId) {
		
		Optional<WorkUpload> work=workRepository.findById(postId);
		if(!work.isPresent()) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Work with given id doesnot exist ");
		}
	     return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,work.get().getComments());			
		
	}
	@Transactional
	public ApiResponse deleteComment(String username,Long postId,  Long commentId) {
		commentRepository.deleteById(commentId);
		Optional<WorkUpload> work=workRepository.findById(postId);
		work.get().setCommentCount(work.get().getComments().size());
		workRepository.save(work.get());
	     return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,"Comment Deleted");			

		
	}
	public ApiResponse updateComment( String username,  Long postId,  Long commentId,CommentRequest comment) {
		Comment comm =commentRepository.getOne(commentId);
		comm.setCommentContent(comment.getCommentContent());
		comm.setCommentMediaType(comment.getCommentMediaType());
		commentRepository.save(comm);
	     return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,comment);			
		
	}
	
	public ApiResponse getCommentByCommentId(Long commentId) {
		Comment comment =commentRepository.getOne(commentId);
	     return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,comment);			

		
	}

	public ApiResponse deleteWork(String username, Long workId) {
		WorkUpload user=workRepository.findByUserNameAndId(username, workId);
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Work delete unsuccessful");
		}
		workRepository.deleteById(workId);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "You work has been deleted successfully");	
	} 
	public ApiResponse getWork(String username, Long workId) {
		WorkUpload user=workRepository.findByUserNameAndId(username, workId);
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Work getting unsuccessful");
		}
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, user);	
	} 
}
