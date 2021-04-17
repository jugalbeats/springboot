package com.example.jugalbeats.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityRequest {

	private List<String> availabilityDates;
    private Boolean isAvailableAll;
}
