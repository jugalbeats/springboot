package com.example.jugalbeats.services;

import com.example.jugalbeats.dao.CalendarRepository;
import com.example.jugalbeats.dao.UsersDao;
import com.example.jugalbeats.models.Calendar;
import com.example.jugalbeats.models.UsersModel;
import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.CalendarForm;
import com.example.jugalbeats.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CalendarService {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    UsersDao usersDao;

    public ApiResponse getUnavailableDatesByUsername(String username){
        if(!Objects.nonNull(username)){
            return new ApiResponse(Constants.INTERNAL_SERVER_ERROR_CODE, "Username is empty");
        }
        List<String> dates = new ArrayList<>();
        List<Calendar> getUnavailableDatesByUser = calendarRepository.findByUserNameArtist(username);
        getUnavailableDatesByUser.stream().forEach(calendar -> dates.add(calendar.getDates()));

        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, dates);
    }

    public ApiResponse setAvailableDatesByUsername(CalendarForm calendarForm, String username){
        if(!Objects.nonNull(username)){
            return new ApiResponse(Constants.INTERNAL_SERVER_ERROR_CODE, "Username is empty");
        }
        UsersModel user= usersDao.findByUsername(username);
        calendarForm.getDates().stream().forEach(date ->
        {
            Calendar calendar = new Calendar();
            calendar.setDates(date);
            calendar.setUserNameArtist(user);
            calendarRepository.save(calendar);
        });

        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, "Posted successfully");
    }

}
