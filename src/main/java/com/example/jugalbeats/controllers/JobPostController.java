package com.example.jugalbeats.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.example.jugalbeats.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jugalbeats.config.security.Authorize;
import com.example.jugalbeats.exception.UnauthorizedException;
import com.example.jugalbeats.models.JobPost;
import com.example.jugalbeats.services.JobPostService;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.Utils;

/*
 * dhruv:2021
 * */
@RestController
@RequestMapping("/api/v1/job-post")
public class JobPostController {

    @Autowired
    private JobPostService jobPostService;

    @PostMapping()
    //@Authorize
    public ApiResponse createJob(@RequestBody JobPostRequest job, HttpServletRequest httpRequest) throws UnauthorizedException {
        //Utils.matchString(httpRequest.getAttribute("username").toString(), job.getUsername());
        ApiResponse response = jobPostService.postJob(job);
        if (Objects.nonNull(response)) {
            return response;
        }
        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

    }

    @GetMapping("/{username}")
    public ApiResponse getJobs(@PathVariable("username") String username) {
        ApiResponse response = jobPostService.getAllJob(username);
        if (Objects.nonNull(response)) {
            return response;
        }
        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

    }

    @DeleteMapping("/{username}/{jobid}")
    //@Authorize
    public ApiResponse deleteJobs(@PathVariable("username") String username, @PathVariable("jobid") String jobid, HttpServletRequest httpRequest) throws UnauthorizedException {
        Utils.matchString(httpRequest.getAttribute("username").toString(), username);
        ApiResponse response = jobPostService.deleteJobById(username, Long.parseLong(jobid));
        if (Objects.nonNull(response)) {
            return response;
        }
        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

    }

    @PostMapping("/{username}/apply")
    //@Authorize
    public ApiResponse applyToJob(@PathVariable("username") String username, @RequestParam Long jobId, HttpServletRequest httpRequest) throws UnauthorizedException {
        //Utils.matchString(httpRequest.getAttribute("username").toString(), username);
        ApiResponse response = jobPostService.applyToJob(jobId, username);
        if (Objects.nonNull(response)) {
            return response;
        }
        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

    }

    @GetMapping("/{username}/getJobApplicants")
    //@Authorize
    public ApiResponse getApplicants(@PathVariable("username") String username, @RequestParam Long jobId, @RequestParam(required = false) String status, HttpServletRequest httpRequest) throws UnauthorizedException {
        //Utils.matchString(httpRequest.getAttribute("username").toString(), username);
        ApiResponse response = jobPostService.getJobApplicant(jobId, username, status);
        if (Objects.nonNull(response)) {
            return response;
        }
        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

    }

    @PutMapping("/{username}/{jobPostId}")
//	@Authorize
    public ApiResponse updateJobPostBooking(@PathVariable("username") String username, @PathVariable("jobPostId") long jobPostId, @RequestBody BookingRequest booking, HttpServletRequest httpRequest ) throws UnauthorizedException {
        //Utils.matchString(httpRequest.getAttribute("username").toString(), username);
        ApiResponse response = jobPostService.updateJobPostBooking(username,jobPostId,booking.getBookingStatus());
        if (Objects.nonNull(response)) {
            return response;
        }
        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE);

    }
}
