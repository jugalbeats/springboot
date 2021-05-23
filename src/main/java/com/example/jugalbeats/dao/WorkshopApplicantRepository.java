package com.example.jugalbeats.dao;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jugalbeats.models.WorkshopApplicant;


public interface WorkshopApplicantRepository  extends JpaRepository<WorkshopApplicant, Long>{

	@Query(value = "select COUNT(*)  from  workshop_applicant r where workshop_id = :workshopId and status = :status",nativeQuery = true)
	long countWorkshopApplicants( @Param("workshopId") long workshopId, 
			@Param("status") String status);
	
	@Query(value = "select apply_by  from  workshop_applicant r where workshop_id = :workshopId and status = :status",nativeQuery = true)
	Set<String> getApplicantNames( @Param("workshopId") long workshopId, 
			@Param("status") String status);
	
	
	@Modifying
	@Query(value="DELETE from workshop_applicant where workshop_id = :workshopId ",nativeQuery = true)
	void deleteWorkshopApplicantByWorkshopid(@Param("workshopId") long workshopId);
}
