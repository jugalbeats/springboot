package com.example.jugalbeats.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "faqs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FAQs extends BaseModel{
	
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_name_faqs", referencedColumnName="user_name", nullable = true)
    private UsersModel userNameFaqs;
	
    @Column(nullable = true)
	private Long quesId;
	
    @Column(nullable = true)
	private String answer;
	
	

}
