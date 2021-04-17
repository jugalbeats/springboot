package com.example.jugalbeats.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
* dhruv
* */
@Entity
@Table(name = "user_availability")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAvailability extends BaseModel {
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username_available", referencedColumnName = "user_name")
    private UsersModel usersModel;
    
    @Column(nullable = true, name = "all_available_status")
    private Boolean isAvailableAll;
    
    @Column(nullable = true, name = "available_list")
    private String availabilityList;
    

}
