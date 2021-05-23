package com.example.jugalbeats.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.dao.WorkshopApplicantRepository;
import com.example.jugalbeats.dao.WorkshopRepository;
import com.example.jugalbeats.enums.ApplicantStatus;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.models.Workshop;
import com.example.jugalbeats.models.WorkshopApplicant;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.WorkshopRequest;
import com.example.jugalbeats.pojo.WorkshopResponse;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@Service
public class WorkshopService {

	@Autowired
	WorkshopRepository workRepo;
	
	@Autowired
	UsersDao usersDao;
	
	@Autowired
	WorkshopApplicantRepository applicantRepo;
	
	public ApiResponse createWorkshop(WorkshopRequest request,String username) {
		UsersModel user=usersDao.findByUsername(username);
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "User not found");
		}
		Workshop workshop=Workshop.builder().dateTime(request.getDateTime()).description(request.getDescription()).eventType(request.getEventType())
				.guestname(request.getGuestname()).hostname(user).imageUrl(request.getImageUrl()).meetLink(request.getMeetLink())
				.paid(request.getPaid()).title(request.getTitle()).build();
		workRepo.save(workshop);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "You workshop has been posted successfully");	

	}
	
	public ApiResponse applyToWorkshop(Long workshopId,String username) {
		UsersModel user=usersDao.findByUsername(username);
		if(Objects.isNull(user)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "User not found");
		}
		Optional<Workshop> workshop=workRepo.findById(workshopId);
		if(Objects.isNull(workshop)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Workshop not found");
		}
		WorkshopApplicant applicant =new WorkshopApplicant();
		applicant.setApplyBy(user);
		applicant.setPostId(workshop.get());
		applicant.setStatus(ApplicantStatus.APPLIED.getValue());
		applicantRepo.save(applicant);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "User Applied successfully");	

		
	}
	@Transactional
	public ApiResponse deleteWorkshop(Long workshopId,String username) {
		Workshop workshop=workRepo.findByUserNameAndId(username, workshopId);
		if(Objects.isNull(workshop)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "username or Workshop is wrong");
		}
		
		if(!applicantRepo.getApplicantNames(workshopId, ApplicantStatus.APPLIED.getValue()).isEmpty())
		{		applicantRepo.deleteWorkshopApplicantByWorkshopid(workshopId);}
		
		workRepo.deleteById(workshopId);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Workshop deleted successfully");	
		
	}
	
	public ApiResponse updateWorkshop(Long workshopId,String username,WorkshopRequest request) {
		Workshop workshop=workRepo.findByUserNameAndId(username, workshopId);
		if(Objects.isNull(workshop)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "username or Workshop is wrong");
		}
		workshop.setDescription(request.getDescription());
		workshop.setDateTime(request.getDateTime());
		workshop.setEventType(request.getEventType());
		workshop.setGuestname(request.getGuestname());
		workshop.setImageUrl(request.getImageUrl());
		workshop.setMeetLink(request.getMeetLink());
		workshop.setPaid(request.getPaid());
		workshop.setTitle(request.getTitle());
		workRepo.save(workshop);
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Workshop updated successfully");	

	}
	
	public ApiResponse getAllWorkshop() {
		List<Workshop> workshop=workRepo.findAll();
		List<WorkshopResponse> response=new ArrayList<>();
		workshop.parallelStream().forEach(shop->{
			Long count=applicantRepo.countWorkshopApplicants(shop.getId(), ApplicantStatus.APPLIED.getValue());
			Set<String> appliedUser=applicantRepo.getApplicantNames(shop.getId(), ApplicantStatus.APPLIED.getValue());
			WorkshopResponse resp= WorkshopResponse.builder().workshopId(shop.getId()).dateTime(shop.getDateTime()).description(shop.getDescription())
					.eventType(shop.getEventType()).guestname(shop.getGuestname()).hostname(shop.getHostname().getUsername()).imageUrl(shop.getImageUrl()).paid(shop.getPaid())
					.meetLink(shop.getMeetLink()).title(shop.getTitle())
					.appliedCount(count)
					.appliedUser(appliedUser).build();
			response.add(resp);
		});
		
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,response);	

	}

	public ApiResponse getWorkshop(String username) {
		List<Workshop> workshop=workRepo.findByHostname(username);
		List<WorkshopResponse> response=new ArrayList<>();
		workshop.parallelStream().forEach(shop->{
			Long count=applicantRepo.countWorkshopApplicants(shop.getId(), ApplicantStatus.APPLIED.getValue());
			Set<String> appliedUser=applicantRepo.getApplicantNames(shop.getId(), ApplicantStatus.APPLIED.getValue());
			WorkshopResponse resp= WorkshopResponse.builder().workshopId(shop.getId()).dateTime(shop.getDateTime()).description(shop.getDescription())
					.eventType(shop.getEventType()).guestname(shop.getGuestname()).hostname(shop.getHostname().getUsername()).imageUrl(shop.getImageUrl()).paid(shop.getPaid())
					.meetLink(shop.getMeetLink()).title(shop.getTitle())
					.appliedCount(count)
					.appliedUser(appliedUser).build();
			response.add(resp);
		});
	        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,response);	
	}
}
