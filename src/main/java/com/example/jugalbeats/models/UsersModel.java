package com.example.jugalbeats.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
 * dhruv
 * */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersModel extends BaseModel implements Serializable{

    @Column(nullable = false, unique = true, name = "user_name")
    private String username;

    @Column(nullable = true,name="email")
    private String email;

    @Column(nullable = true,name="password")
    @JsonIgnore
    private String password;

    @Column(nullable = true,name="customer_type")
    private String customerType;

    @Column(nullable = true, name="mobile")
    private String mobile;

    @Column(nullable = true, name="dob")
    private String dateOfBirth;

    @Column(nullable = true,name="full_name")
    private String fullName;

    @Column(nullable = true,name="paid_user")
    private Boolean paidUser;

    @Column(nullable = true,name="subscription_pack")
    private String subscriptionPack;

    @Column(nullable = true,name="profile_image")
    private String profileImage;

    @Column(nullable = true,name="profile_video")
    private String profileVideo;

    @Column(nullable = true,name="is_private")
    private Boolean isPrivate;

    @Column(nullable = true,name="location")
    private String location;

    @Column(nullable = true,name="gender")
    private String gender;

    @Column(nullable = true,name="stars")
    private Double stars;
    
    @Column(nullable = true,name="profession")
    private String profession;

    @Column(nullable = true, name = "status")
    private String status;

    @Column(nullable = true, name = "description")
    private String description;
    
    @Column(nullable = true,name="cover_image")
    private String coverImage;
    
    @Column(nullable = true, name = "genre")
    private String genre;
    
    @Column(nullable = true, name = "art_type")
    private String artType;
}
