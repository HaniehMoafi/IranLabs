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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    private RecommendationDto selectedRecommend;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        recommendationDtos = new ArrayList<>();
        GetAllRecommendation response = restTemplate.getForObject(Constants.BASE_URL + Constants.GET_ALL_REC, GetAllRecommendation.class);
        recommendationDtos = response.getRecommendations();

    }

    public String update() {
        AddResponseRequest request = new AddResponseRequest();
        if (Objects.isNull(selectedRecommend) || !StringUtils.hasText(selectedRecommend.getResponse())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "please select at least one row"));
            return "";
        } else {
            request.setResponse(selectedRecommend.getResponse());
            request.setRecommendationId(selectedRecommend.getId());

            HttpEntity<AddResponseRequest> entity = new HttpEntity<>(request, new HttpHeaders());

            BaseResponse response = restTemplate.postForObject(Constants.BASE_URL + Constants.ADD_RES,
                    entity,
                    BaseResponse.class);
            if (StringUtils.hasText(response.getMessage())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", response.getMessage()));
                emptyPage();
                return "";
            }
            emptyPage();
            return "/response-add.xhtml?faces-redirect=true";
        }
    }

    private void emptyPage() {
        selectedRecommend = null;

    }

    public List<RecommendationDto> getRecommendationDtos() {
        return recommendationDtos;
    }

    public void setRecommendationDtos(List<RecommendationDto> recommendationDtos) {
        this.recommendationDtos = recommendationDtos;
    }

    public RecommendationDto getSelectedRecommend() {
        return selectedRecommend;
    }

    public void setSelectedRecommend(RecommendationDto selectedRecommend) {
        this.selectedRecommend = selectedRecommend;
    }
}
