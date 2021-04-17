package com.example.jugalbeats.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsModel extends BaseModel{

    @Column(nullable = true)
    private String header;

    @Column(nullable = true)
    private String caption;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_name_posts", referencedColumnName="user_name", nullable = true)
    private UsersModel userNamePosts;

    @Column(nullable = true)
    private String mediaType;

}
