package com.iranLabs.assignment.persistence.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationDate;


    @PrePersist
    public void prePersist() {
        setCreationDate(new Date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
