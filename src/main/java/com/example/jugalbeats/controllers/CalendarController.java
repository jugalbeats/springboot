package com.example.jugalbeats.controllers;

import com.example.jugalbeats.pojo.ApiResponse;
import com.example.jugalbeats.pojo.CalendarForm;
import com.example.jugalbeats.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calendar")
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @GetMapping("/unavailable/{username}")
    public ApiResponse getAllUnavailableDatesForUser(@PathVariable("username") String username){
        return calendarService.getUnavailableDatesByUsername(username);
    }

    @PostMapping("/{username}")
    public ApiResponse setAvailableDate(@RequestBody CalendarForm calendarForm,
                                        @PathVariable("username") String username){
        return calendarService.setAvailableDatesByUsername(calendarForm, username);
    }

    @DeleteMapping("/{username}/{date}")
    public ApiResponse deleteDateForUser(@PathVariable("username") String username,
                                         @PathVariable("date") String date){

    }

}
