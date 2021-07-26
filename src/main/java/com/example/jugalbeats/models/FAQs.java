package com.example.jugalbeats.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "faqs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FAQs extends BaseModel{
	
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="user_name_faqs", referencedColumnName="user_name", nullable = true)
	@JsonBackReference
    private UsersModel userNameFaqs;
	
    @Column(nullable = false)
	private Long quesId;
	
    @Column(nullable = false)
	private String answer;
	
	

}
