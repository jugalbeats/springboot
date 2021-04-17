package com.example.jugalbeats.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import com.example.jugalbeats.pojo.model.PriceInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Entity
@Table(name = "preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferncesModel extends BaseModel{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name_preference", referencedColumnName = "user_name")
    private UsersModel usersModel;

    @Column(nullable = true, name = "willing_to_travel")
    private Boolean willingToTravel;

    @Column(nullable = true, name = "performance_duration")
    private String performanceDuration;

    @Column(nullable = true, name = "members")
    private String members;

    @Column(nullable = true, name = "preferred_events")
    private String prefferedEvents;
    
	public List<Long>  getPrefferedEvents(){
		if(!StringUtils.isNotEmpty(prefferedEvents)) {
			return new ArrayList();
		}
		Gson gson =new Gson();
		return gson.fromJson(prefferedEvents, new TypeToken<ArrayList<Long>>(){}.getType());
		
	}

}
