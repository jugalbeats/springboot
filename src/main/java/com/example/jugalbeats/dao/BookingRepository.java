package com.example.jugalbeats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.jugalbeats.models.Booking;

public interface BookingRepository extends CrudRepository<Booking,Long>{
	
	
	@Query(value = "select DISTINCT b.* from  booking b where user_name_client = :username  ",nativeQuery = true)
	List<Booking> getBookingByClientUsername(String username);
	
	@Query(value = "select DISTINCT b.* from  booking b where user_name_artist = :username  ",nativeQuery = true)
	List<Booking> getBookingByArtistUsername(String username);

}
