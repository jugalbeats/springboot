package com.example.jugalbeats.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.JobPostRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.models.JobPost;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.JobPostRequest;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@Service
public class JobPostService {
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
    @Autowired
    private UsersDao usersDao;
	
	
	public ApiResponse postJob(JobPostRequest job) {
		UsersModel user=usersDao.findByUsername(job.getUsername());
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Job post unsuccessful");
		}
		JobPost newJob=new JobPost();
		newJob.setDescription(job.getDescription());
		newJob.setDuration(job.getDuration());
		newJob.setLandmark(job.getLandmark());
		newJob.setLocation(job.getLocation());
		newJob.setSpecialAmenities(job.getSpecialAmenities());
		newJob.setRequirement(job.getRequirement());
		newJob.setOccasion(job.getOccasion());
		newJob.setTitle(job.getTitle());
		newJob.setPayment(job.getPayment());
		newJob.setUserNameJobPost(user);
		jobPostRepository.save(newJob);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "You job has been posted successfully");	
	}
	
	public ApiResponse getAllJob(String userName) {
		
		UsersModel user=usersDao.findByUsername(userName);
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "User not found");

		}
	    List<JobPost>posts=jobPostRepository.getAllByUserNameJobPost(user);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,posts);
		
	}

	public ApiResponse deleteJobById(String username, long id) {
		// TODO Auto-generated method stub
		JobPost post=jobPostRepository.findJobPostByUsernameAndJobId(username, id);
		if(Objects.isNull(post)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "job not found");
		}
		jobPostRepository.delete(post);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,"job post deleted successfully");
	}

}
