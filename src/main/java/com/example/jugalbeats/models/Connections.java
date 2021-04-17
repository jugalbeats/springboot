package com.example.jugalbeats.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * Dhruv:2021
 * */
@Entity
@Table(name = "connections")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Connections extends BaseModel{

	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
    @JoinColumn(name="sender", referencedColumnName="user_name", nullable = false)
	private UsersModel sender;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)    
    @JoinColumn(name="receiver", referencedColumnName="user_name", nullable = false)
	private UsersModel receiver;

    @Column(nullable = false, name = "status")
    private int status;

	
	
}
