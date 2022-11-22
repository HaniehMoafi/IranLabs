package com.iranLabs.assignment.business.model.response;

import com.iranLabs.assignment.business.model.RecommendationDto;

import java.util.List;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public class GetAllRecommendation extends BaseResponse {

    private List<RecommendationDto> recommendations;

    public List<RecommendationDto> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<RecommendationDto> recommendations) {
        this.recommendations = recommendations;
    }
}
