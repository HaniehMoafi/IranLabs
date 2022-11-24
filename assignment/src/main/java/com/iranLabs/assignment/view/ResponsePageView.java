package com.iranLabs.assignment.view;

import com.iranLabs.assignment.business.model.RecommendationDto;
import com.iranLabs.assignment.business.model.request.AddResponseRequest;
import com.iranLabs.assignment.business.model.response.BaseResponse;
import com.iranLabs.assignment.business.model.response.GetAllRecommendation;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/23/2022
 */

@Scope(value = "session")
@Component(value = "responsePageView")
@ELBeanName(value = "responsePageView")
@Join(path = "/response", to = "/response-add.jsf")
public class ResponsePageView {


    @Autowired
    private RestTemplate restTemplate;
    private List<RecommendationDto> recommendationDtos;
    private String newResponse;

    private RecommendationDto selectedRecommend;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        recommendationDtos = new ArrayList<>();
        GetAllRecommendation response = restTemplate.getForObject("http://localhost:8080/recommendation/getAll", GetAllRecommendation.class);
        recommendationDtos = response.getRecommendations();

    }

    public String update() {
        AddResponseRequest request = new AddResponseRequest();
        //todo check null
        request.setResponse(selectedRecommend.getResponse());
        request.setRecommendationId(selectedRecommend.getId());
        HttpHeaders headers = new HttpHeaders();
        //todo
        headers.set("Authorization", "JWT eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyRGV0YWlscyIsInBhc3N3b3JkIjoiYWRtaW4iLCJpc3MiOiJJcmFuTGFicy1hc3NpZ25tZW50IiwiaWF0IjoxNjY5MTMxOTk2LCJ1c2VybmFtZSI6ImFkbWluIn0.ygmjKVX5dqUAhpfSmcei9Za2rG6rDkVgur6ZUIh2pL8");

        HttpEntity<AddResponseRequest> entity = new HttpEntity<>(request, headers);

        BaseResponse response = restTemplate.postForObject("http://localhost:8080/response/add",
                entity,
                BaseResponse.class);
        emptyPage();
        return "/response-add.xhtml?faces-redirect=true";
    }
    private void emptyPage() {
        newResponse = "";
        selectedRecommend = null;

    }
    public void onSelectRecTableRowDbClick(final SelectEvent event) {
        if (event.getObject() != null) {
            selectedRecommend = (RecommendationDto) event.getObject();
            RequestContext.getCurrentInstance().closeDialog(selectedRecommend);
        }
    }

    public List<RecommendationDto> getRecommendationDtos() {
        return recommendationDtos;
    }

    public void setRecommendationDtos(List<RecommendationDto> recommendationDtos) {
        this.recommendationDtos = recommendationDtos;
    }

    public String getNewResponse() {
        return newResponse;
    }

    public void setNewResponse(String newResponse) {
        this.newResponse = newResponse;
    }

    public RecommendationDto getSelectedRecommend() {
        return selectedRecommend;
    }

    public void setSelectedRecommend(RecommendationDto selectedRecommend) {
        this.selectedRecommend = selectedRecommend;
    }
}
