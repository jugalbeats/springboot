package com.example.jugalbeats.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.jugalbeats.enums.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jugalbeats.dao.BookingRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.enums.UserType;
import com.example.jugalbeats.models.Booking;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.BookingRequest;
import com.example.jugalbeats.utils.Constants;
/*
 * dhruv:2021
 * */
@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private EmailService emailService;
	
	  @Autowired
	  private UsersDao usersDao;

	public ApiResponse getAllBooking(String username, String userType, Long dateTime) {
		List<Booking> bookings=new ArrayList<>();
		if(userType.equalsIgnoreCase(UserType.ARTIST.getValue())) {
			if(dateTime>0)
				bookings=bookingRepository.getBookingByArtistUsernameDateTime(username, dateTime);
			else bookings=bookingRepository.getBookingByArtistUsername(username);
		}
		if(userType.equalsIgnoreCase(UserType.CLIENT.getValue())) {
			bookings=bookingRepository.getBookingByClientUsername(username);
		}
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE,bookings);

		
		
	}

	public ApiResponse createBooking(BookingRequest bookingRequest) {
		UsersModel userArtist=usersDao.findByUsername(bookingRequest.getUsernameArtist());
		if(Objects.isNull(userArtist)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Invalid Artist");
		}
		UsersModel userClient=usersDao.findByUsername(bookingRequest.getUsernameClient());
		if(Objects.isNull(userClient)) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Invalid Client");
		}
		Booking booking =new Booking();
		booking.setCaption(bookingRequest.getCaption());
		booking.setDateTime(bookingRequest.getDateTime());
		booking.setDuration(bookingRequest.getDuration());
		booking.setLocation(bookingRequest.getLocation());
		booking.setPaymentStatus(bookingRequest.getPaymentStatus());
		booking.setUserNameClient(userClient);
		booking.setUserNameArtist(userArtist);
        booking.setEventType(bookingRequest.getEventType());
        booking.setIsDeleted(false);
        booking.setCreatedBy(bookingRequest.getUsernameClient());
        booking.setBookingStatus(BookingStatus.PENDING.getValue());
        bookingRepository.save(booking);
        emailService.sendEmail(Constants.NEW_BOOKING_MAIL_MESSAGE, Constants.NEW_BOOKING_MAIL_SUBJECT,userArtist.getEmail());
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Booking Request is Successful");	

	}

	public ApiResponse updateBooking(String username, long bookingId, BookingRequest bookingRequest) {
		
		Optional<Booking> booking=bookingRepository.findById(bookingId);
		if(!booking.isPresent()) {
	        return new ApiResponse(Constants.FAILURE_CODE, Constants.FAILURE_MESSAGE, "Invalid Booking Id");			
		}
		booking.get().setCaption(bookingRequest.getCaption());
		booking.get().setDateTime(bookingRequest.getDateTime());
		booking.get().setDuration(bookingRequest.getDuration());
		booking.get().setLocation(bookingRequest.getLocation());
		booking.get().setPaymentStatus(bookingRequest.getPaymentStatus());
        booking.get().setEventType(bookingRequest.getEventType());
        booking.get().setUpdatedBy(username);
        booking.get().setIsDeleted(bookingRequest.getIsDeleted());
        booking.get().setBookingStatus(mapBookinStatus(bookingRequest.getBookingStatus()));
        bookingRepository.save(booking.get());
        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Booking Request is Successful");	

	}

	public String mapBookinStatus(int bookingStatusCode){
		String bookingStatus = BookingStatus.PENDING.getValue();
		if(bookingStatusCode==0){
			bookingStatus = BookingStatus.ACCEPTED.getValue();
		}
		else if(bookingStatusCode==1){
			bookingStatus = BookingStatus.DECLINED.getValue();
		}
		return bookingStatus;
	}
	  
	  
	
	

}
