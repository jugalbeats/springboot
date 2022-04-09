package com.example.jugalbeats.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "followers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersModel {

    @EmbeddedId
    private Followers followers;
    private String status;

}
