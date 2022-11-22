package com.iranLabs.assignment.business.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public class AddResponseRequest implements Serializable {

    @NotNull
    private Integer recommendationId;
    @NotBlank
    private String response;

    public Integer getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Integer recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
