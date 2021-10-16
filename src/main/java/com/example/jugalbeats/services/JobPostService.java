package com.example.jugalbeats.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.JobApplicantRepository;
import com.example.jugalbeats.dao.JobPostRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.enums.ApplicantStatus;
import com.example.jugalbeats.models.JobApplicant;
import com.example.jugalbeats.models.JobPost;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.models.Workshop;
import com.example.jugalbeats.models.WorkshopApplicant;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.GetApplicantResponse;
import com.example.jugalbeats.pojo.JobPostRequest;
import com.example.jugalbeats.utils.Constants;
import com.google.gson.JsonObject;
/*
 * dhruv:2021
 * */
@Service
public class JobPostService {
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
    @Autowired
    private UsersDao usersDao;
    
    @Autowired
    private JobApplicantRepository applicantRepo;
	
	
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
	
	public ApiResponse applyToJob(Long workshopId,String username) {
		UsersModel user=usersDao.findByUsername(username);
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "User not found");
		}
		Optional<JobPost> workshop=jobPostRepository.findById(workshopId);
		if(Objects.isNull(workshop)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Job not found");
		}
		JobApplicant applicant =new JobApplicant();
		applicant.setApplyBy(user);
		applicant.setPostId(workshop.get());
		applicant.setStatus(ApplicantStatus.APPLIED.getValue());
		applicantRepo.save(applicant);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "User Applied successfully");	

		
	}

	public ApiResponse deleteJobById(String username, long id) {
		JobPost post=jobPostRepository.findJobPostByUsernameAndJobId(username, id);
		if(Objects.isNull(post)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "job not found");
		}
		if(!applicantRepo.getApplicantNames(id, ApplicantStatus.APPLIED.getValue()).isEmpty())
		{		applicantRepo.deleteJobApplicantByJobid(id);}
		jobPostRepository.deleteById(id);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,"job post deleted successfully");
	}
 // create enum for status
	public ApiResponse getJobApplicant(Long jobId, String username,String status) {
		JobPost workshop=jobPostRepository.findJobPostByUsernameAndJobId(username, jobId);
		if(Objects.isNull(workshop)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Job not found");
		}
		List<JobApplicant> jobApp=null;
		if(!status.isEmpty() && Objects.nonNull(status)) {
			jobApp =applicantRepo.getApplicantNames(jobId, status);
		    }
		else {jobApp=applicantRepo.getApplicantNames(jobId);}
		    List<GetApplicantResponse> response=new ArrayList<>();
		jobApp.parallelStream().forEach(app-> {
			response.add(GetApplicantResponse.builder().fullname(app.getApplyBy().getFullName()).genre(app.getApplyBy().getGenre())
					.imageUrl(app.getApplyBy().getProfileImage()).location(app.getApplyBy().getLocation()).profession(app.getApplyBy().getProfession())
					.username(app.getApplyBy().getUsername()).build());
		});
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, response);	
	}

}
