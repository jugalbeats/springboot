package com.example.jugalbeats.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images_videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagesVideosModel extends BaseModel{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_name_media", referencedColumnName="user_name", nullable = true)
    private UsersModel userNameMedia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId", nullable = true)
    private PostsModel postsModelId;

    @Lob
    @Column(nullable = true, length = 512)
    private String images;

    @Lob
    @Column(nullable = true, length = 512)
    private String videos;
}
