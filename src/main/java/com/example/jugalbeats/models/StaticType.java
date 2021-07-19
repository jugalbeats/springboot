package com.example.jugalbeats.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* dhruv
* */
@Entity
@Table(name = "static_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaticType extends BaseModel{
	
	  @Column(nullable = true, name = "genre")
	  private String genre;
	  

	  @Column(nullable = true, name = "profession_type")
	  private int professionType;

}
