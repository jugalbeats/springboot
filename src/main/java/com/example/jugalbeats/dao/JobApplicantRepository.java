package com.example.jugalbeats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jugalbeats.models.JobApplicant;

import javax.transaction.Transactional;

@Transactional
public interface JobApplicantRepository  extends JpaRepository<JobApplicant, Long>{

	@Query(value = "select COUNT(*)  from  job_applicant r where job_post_id = :jobId and status = :status",nativeQuery = true)
	long countWorkshopApplicants( @Param("jobId") long jobId, 
			@Param("status") String status);
	
	@Query(value = "select DISTINCT r.*  from  job_applicant r where job_post_id = :jobId",nativeQuery = true)
	List<JobApplicant> getApplicantNames( @Param("jobId") long jobId 
			);
	
	@Query(value = "select DISTINCT r.*  from  job_applicant r where job_post_id = :jobId and status = :status",nativeQuery = true)
	List<JobApplicant> getApplicantNames( @Param("jobId") long jobId,
			@Param("status") String status);

	@Query(value = "Select * from job_applicant where job_post_id = ?1 and apply_by = ?2", nativeQuery = true)
	JobApplicant getApplicants(long jobId, String username);
	
	@Modifying
	@Query(value="DELETE from job_applicant where job_post_id = :jobId ",nativeQuery = true)
	void deleteJobApplicantByJobid(@Param("jobId") long jobId);
}
