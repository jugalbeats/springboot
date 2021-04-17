package com.example.jugalbeats.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "preferred_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferredEvents {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="id")
	    private long id;
	    
	    @Column(name="name",nullable = true)
	    private String name;
	    
	    @Column(name="url",nullable = true)
	    private String url;
}
