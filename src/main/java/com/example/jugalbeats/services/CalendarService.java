package com.example.jugalbeats.services;

import com.example.jugalbeats.dao.CalendarRepository;
import com.example.jugalbeats.models.Calendar;
import com.example.jugalbeats.pojo.ApiResponse;
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

    public ApiResponse getUnavailableDatesByUsername(String username){
        if(!Objects.nonNull(username)){
            return new ApiResponse(Constants.INTERNAL_SERVER_ERROR_CODE, "Username is empty");
        }
        List<String> dates = new ArrayList<>();
        List<Calendar> getUnavailableDatesByUser = calendarRepository.findByUserName(username);
        getUnavailableDatesByUser.stream().forEach(calendar -> dates.add(calendar.getDates()));

        return new ApiResponse(Constants.SUCCESS_CODE, Constants.SUCCESS_MESSAGE, dates);
    }

}
