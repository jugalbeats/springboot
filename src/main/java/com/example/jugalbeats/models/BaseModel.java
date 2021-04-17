package com.example.jugalbeats.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @CreationTimestamp
    @Column(name="created")
    private Date created;

    @UpdateTimestamp
    @Column(name="modified")
    private Date modified;

    @Column(name="is_deleted", nullable = true)
    private Boolean isDeleted;
    
    @PrePersist
	  protected void onCreate() {
	    created = new Date();
	    modified = new Date();
	  }

	  @PreUpdate
	  protected void onUpdate() {
		  modified = new Date();
	  }

}
