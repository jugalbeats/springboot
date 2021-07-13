package com.example.jugalbeats.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.jugalbeats.models.WorkUpload;

public interface WorkUploadRepository extends CrudRepository<WorkUpload, Long> {
	
	@Query(value = "select DISTINCT w.* from  work_upload w where user_name_work_upload = :username  ",nativeQuery = true)
    public List<WorkUpload> findByUserName(String username);

	
	@Query(value = "select DISTINCT w.* from  work_upload w where user_name_work_upload = :username  and id =:id ",nativeQuery = true)
    public WorkUpload findByUserNameAndId(@Param("username")String username,@Param("id")Long id);
	
	@Query(value = "select DISTINCT w.* from  work_upload w ",nativeQuery = true)
    public Page<WorkUpload> findPromotionalWork(Pageable pageable);
}
