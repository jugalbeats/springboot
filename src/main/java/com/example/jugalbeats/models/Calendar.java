package com.example.jugalbeats.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "calendar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dates;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name_artist", referencedColumnName = "user_name", nullable = false)
    private UsersModel userNameArtist;
}
