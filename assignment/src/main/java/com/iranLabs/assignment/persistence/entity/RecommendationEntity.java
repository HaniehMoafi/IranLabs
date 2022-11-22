package com.iranLabs.assignment.persistence.entity;

import com.iranLabs.assignment.business.serviceEnums.RecommendationStatusEnum;

import javax.persistence.*;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@Entity
@Table(name = "RECOMMENDATION_ENTITY")
public class RecommendationEntity extends BaseEntity {


    @Column(name = "RECOMMENDATION", length = 100)
    private String recommendation;


    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private RecommendationStatusEnum status = RecommendationStatusEnum.WAITING;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;


    @OneToOne
    @JoinColumn(name = "RESPONSE_ID")
    private ResponseEntity response;


    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public RecommendationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RecommendationStatusEnum status) {
        this.status = status;
    }
}
