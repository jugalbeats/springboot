package com.example.jugalbeats.controllers;

import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calendar")
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @GetMapping("/unavailable/{username}")
    public ApiResponse getAllUnavailableDatesForUser(@PathVariable("username") String username){
        return calendarService.getUnavailableDatesByUsername(username);
    }

}
