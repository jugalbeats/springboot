package com.example.jugalbeats.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.example.jugalbeats.controllers.JobPostController;
import com.example.jugalbeats.dao.BookingRepository;
import com.example.jugalbeats.enums.BookingStatus;
import com.example.jugalbeats.models.Booking;
import com.example.jugalbeats.pojo.BookingRequest;
import com.google.api.client.util.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.JobApplicantRepository;
import com.example.jugalbeats.dao.JobPostRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.enums.ApplicantStatus;
import com.example.jugalbeats.models.JobApplicant;
import com.example.jugalbeats.models.JobPost;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.GetApplicantResponse;
import com.example.jugalbeats.pojo.JobPostRequest;
import com.example.jugalbeats.utils.Constants;

/*
 * dhruv:2021
 * */
@Service
public class JobPostService {

	Logger logger = LoggerFactory.getLogger(JobPostService.class);

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private JobPostRepository jobPostRepository;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private JobApplicantRepository applicantRepo;

	public ApiResponse postJob(JobPostRequest job) {
		UsersModel user = usersDao.findByUsername(job.getUsername());
		if (Objects.isNull(user)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Job post unsuccessful");
		}
		JobPost newJob = new JobPost();
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
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,
				"You job has been posted successfully");
	}

	public ApiResponse getAllJob(String userName) {

		UsersModel user = usersDao.findByUsername(userName);
		if (Objects.isNull(user)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "User not found");

		}
		List<JobPost> posts = jobPostRepository.getAllByUserNameJobPost(user);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, posts);

	}

	public ApiResponse applyToJob(Long workshopId, String username) {
		UsersModel user = usersDao.findByUsername(username);
		if (Objects.isNull(user)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "User not found");
		}
		Optional<JobPost> workshop = jobPostRepository.findById(workshopId);
		if (Objects.isNull(workshop)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Job not found");
		}
		JobApplicant applicant = applicantRepo.getApplicants(workshopId, username);
		if(applicant!=null){
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "This user has already applied for this job once");
		}
		applicant = new JobApplicant();
		applicant.setApplyBy(user);
		applicant.setPostId(workshop.get());
		applicant.setStatus(BookingStatus.PENDING.getValue());
		applicantRepo.save(applicant);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "User Applied successfully");

	}

	public ApiResponse deleteJobById(String username, long id) {
		JobPost post = jobPostRepository.findJobPostByUsernameAndJobId(username, id);
		if (Objects.isNull(post)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "job not found");
		}
		if (!applicantRepo.getApplicantNames(id).isEmpty()) {
			applicantRepo.deleteJobApplicantByJobid(id);
		}
		jobPostRepository.deleteById(id);
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "job post deleted successfully");
	}

	// create enum for status
	public ApiResponse getJobApplicant(Long jobId, String username, String status) {
		JobPost workshop = jobPostRepository.findJobPostByUsernameAndJobId(username, jobId);
		if (Objects.isNull(workshop)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Job not found");
		}
		List<JobApplicant> jobApp = new ArrayList<>();
		if (!Objects.isNull(status)) {
			jobApp = applicantRepo.getApplicantNames(jobId, status);
		} else {
			jobApp = applicantRepo.getApplicantNames(jobId);
		}
		List<GetApplicantResponse> response = new ArrayList<>();
		if (Objects.isNull(jobApp)) {
			return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "no applicant found");
		}
		jobApp.parallelStream().forEach(app -> {
			response.add(GetApplicantResponse.builder()
					.fullname(app.getApplyBy().getFullName())
					.genre(app.getApplyBy().getGenre()).imageUrl(app.getApplyBy().getProfileImage())
					.location(app.getApplyBy().getLocation()).profession(app.getApplyBy().getProfession())
					.username(app.getApplyBy().getUsername())
					.status(app.getStatus()).build());
		});
		return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, response);
	}


	public ApiResponse updateJobPostBooking(String username, long jobPostId, int bookingStatus) {
		ApiResponse response = null;
		logger.info("username : " + username);
		logger.info("id : " + jobPostId);
		JobApplicant applicant = applicantRepo.getApplicants(jobPostId, username);
		logger.info(applicant.toString());
		logger.info(mapBookinStatus(bookingStatus));
		applicant.setStatus(mapBookinStatus(bookingStatus));
		applicantRepo.save(applicant);
		if(bookingStatus==0){
			JobPost jobPost = jobPostRepository.findJobPostByJobId(jobPostId);
			Booking bookingRequest = new Booking();
			bookingRequest.setCaption("Booked for jobId: " + jobPostId);
			bookingRequest.setDateTime(System.currentTimeMillis());
			bookingRequest.setDuration(jobPost.getDuration());
			bookingRequest.setLocation(jobPost.getLocation());
			bookingRequest.setPaymentStatus(null);
			bookingRequest.setUserNameClient(jobPost.getUserNameJobPost());
			bookingRequest.setUserNameArtist(usersDao.findByUsername(username));
			bookingRequest.setEventType(jobPost.getOccasion());
			bookingRequest.setIsDeleted(false);
			bookingRequest.setCreatedBy(jobPost.getUserNameJobPost().getUsername());
			bookingRequest.setBookingStatus(BookingStatus.ACCEPTED.getValue());
			bookingRepository.save(bookingRequest);
			return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Booking Request is Successful");
		}
		return response;
	}

	private String mapBookinStatus(int bookingStatusCode){
		String bookingStatus = BookingStatus.PENDING.getValue();
		if(bookingStatusCode==0){
			bookingStatus = BookingStatus.ACCEPTED.getValue();
		}
		else if(bookingStatusCode==1){
			bookingStatus = BookingStatus.DECLINED.getValue();
		}
		logger.info("booking mapped to : " + bookingStatus);
		return bookingStatus;
	}
}
