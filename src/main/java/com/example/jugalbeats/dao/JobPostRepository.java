package com.example.jugalbeats.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.jugalbeats.models.JobPost;
import com.example.jugalbeats.models.UsersModel;

public interface JobPostRepository extends CrudRepository<JobPost, Long>{
	
	List<JobPost> getAllByUserNameJobPost(UsersModel userNameJobPost);
	
	@Query(value="select DISTINCT j.* from  job_post j where requirement iLIKE %:searchStr% and payment between :min and :max",nativeQuery = true)
	Page<JobPost> findJobPostByTitle(@Param("searchStr") String searchStr,@Param("min") long min,@Param("max") long max,Pageable pageable);
    
	@Query(value="select DISTINCT j.* from  job_post j where requirement iLIKE %:searchStr%",nativeQuery = true)
	Page<JobPost> findJobPostByTitle(@Param("searchStr") String searchStr,Pageable pageable);
	
	@Query(value="select DISTINCT j.* from  job_post j where id = :id and user_name_job_post = :username",nativeQuery = true)
	JobPost findJobPostByUsernameAndJobId(@Param("username")String username,@Param("id")Long id);

	@Query(value="select DISTINCT j.* from  job_post j where id = :id",nativeQuery = true)
	JobPost findJobPostByJobId(@Param("id")Long id);

}
