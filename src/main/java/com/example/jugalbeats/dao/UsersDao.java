package com.example.jugalbeats.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.jugalbeats.models.JobPost;
import com.example.jugalbeats.models.UsersModel;

import javax.transaction.Transactional;

@Transactional
public interface UsersDao extends CrudRepository<UsersModel, Long> {

     UsersModel findByEmail(String email);
    
     UsersModel getUsersDaoByEmailAndPassword(String email,String password);
    
     UsersModel getUsersDaoByUsernameAndPassword(String username, String password);

     UsersModel findByUsername(String userName);

     Page<UsersModel> findByCustomerType(String customerType, Pageable pageable);
 	
     @Query(value="select DISTINCT j.* from  users j where customer_type iLIKE %:customerType% and profession iLIKE %:profession% ",nativeQuery = true)
 	 Page<UsersModel> findByProfessionAndCustomerType(@Param("profession") String profession,@Param("customerType") String customerType,Pageable pageable);   

     @Query(value="select DISTINCT j.* from  users j where customer_type iLIKE %:customerType% and location iLIKE %:location% ",nativeQuery = true)
   	 Page<UsersModel> findByLocationAndCustomerType(@Param("location") String location,@Param("customerType") String customerType,Pageable pageable);   

     @Query(value="select DISTINCT j.* from  users j where customer_type iLIKE %:customerType% and (location iLIKE %:location% or profession iLIKE %:profession% )",nativeQuery = true)
   	 Page<UsersModel> findByLocationAndCustomerTypeAndProfession(@Param("location") String location,@Param("customerType") String customerType,@Param("profession") String profession,Pageable pageable);   

     @Query(value="select DISTINCT j.* from  users j inner join user_availability u on j.user_name=u.username_available  where customer_type iLIKE %:customerType% and available_list iLIKE %:available_date% ",nativeQuery = true)
   	 Page<UsersModel> findByCustomerTypeAndAvailability(@Param("available_date") String available_date,@Param("customerType") String customerType,Pageable pageable);   


     @Query(value="select DISTINCT j.* from  users j inner join user_availability u on j.user_name=u.username_available  where customer_type iLIKE %:customerType% and (available_list iLIKE %:available_date% or profession iLIKE %:profession% ) ",nativeQuery = true)
   	 Page<UsersModel> findByCustomerTypeAndAvailability(@Param("available_date") String available_date,@Param("customerType") String customerType,@Param("profession") String profession,Pageable pageable);   

     @Query(value="select DISTINCT j.* from  users j inner join user_availability u on j.user_name=u.username_available  where customer_type iLIKE %:customerType% and (available_list iLIKE %:available_date% or location iLIKE %:location% ) ",nativeQuery = true)
   	 Page<UsersModel> findByCustomerTypeAndAvailabilityAndLocation(@Param("available_date") String available_date,@Param("customerType") String customerType,@Param("location") String location,Pageable pageable);   

     @Query(value="select DISTINCT j.* from  users j inner join user_availability u on j.user_name=u.username_available  where customer_type iLIKE %:customerType% and (available_list iLIKE %:available_date% or profession iLIKE %:profession%  or location iLIKE %:location% ) ",nativeQuery = true)
   	 Page<UsersModel> findByCustomerTypeAndAvailability(@Param("available_date") String available_date,@Param("customerType") String customerType,@Param("profession") String profession,@Param("location") String location,Pageable pageable);   

}
