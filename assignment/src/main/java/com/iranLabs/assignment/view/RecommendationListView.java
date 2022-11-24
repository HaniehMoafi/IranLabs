package com.iranLabs.assignment.view;

import com.iranLabs.assignment.business.model.RecommendationDto;
import com.iranLabs.assignment.business.model.request.AddRecommendationRequest;
import com.iranLabs.assignment.business.model.response.BaseResponse;
import com.iranLabs.assignment.business.model.response.GetAllRecommendation;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/23/2022
 */

@Scope(value = "session")
@Component(value = "recommendationList")
@ELBeanName(value = "recommendationList")
@Join(path = "/", to = "/recommendation-list.jsf")
public class RecommendationListView {

    @Autowired
    private RestTemplate restTemplate;
    private List<RecommendationDto> recommendationDtos;
    private String name;
    private String recommendation;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
         recommendationDtos = new ArrayList<>();



/*
        RestTemplate restTemplate = new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();*/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);
        GetAllRecommendation response = restTemplate.exchange("http://localhost:8080/recommendation/getAll",
               HttpMethod.GET,requestEntity, GetAllRecommendation.class).getBody();

        recommendationDtos = response.getRecommendations();

    }

    public String save() {
        AddRecommendationRequest request = new AddRecommendationRequest();
        request.setName(name);
        request.setRecommendation(recommendation);
        BaseResponse response = restTemplate.postForEntity("http://localhost:8080/recommendation/add", request,
                BaseResponse.class).getBody();
        emptyPage();
        return "/recommendation-list.xhtml?faces-redirect=true";
    }



    private void emptyPage() {
        name = "";
        recommendation = "";

    }

    public List<RecommendationDto> getRecommendationDtos() {
        return recommendationDtos;
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
}
