package com.iranLabs.assignment.business.model;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public class RecommendationDto {


    private Integer id;
    private String name;
    private String recommendation;
    private String response;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
