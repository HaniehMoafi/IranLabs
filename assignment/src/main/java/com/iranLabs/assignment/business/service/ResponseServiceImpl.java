package com.iranLabs.assignment.business.service;

import com.iranLabs.assignment.business.model.request.AddResponseRequest;
import com.iranLabs.assignment.business.serviceEnums.RecommendationStatusEnum;
import com.iranLabs.assignment.exception.RecommendationServiceException;
import com.iranLabs.assignment.persistence.entity.RecommendationEntity;
import com.iranLabs.assignment.persistence.entity.ResponseEntity;
import com.iranLabs.assignment.persistence.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@Service
public class ResponseServiceImpl implements ResponseService{

    private final ResponseRepository responseRepository;
    private final RecommendationService recommendationService;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository,
                               RecommendationService recommendationService) {
        this.responseRepository = responseRepository;
        this.recommendationService = recommendationService;
    }

    @Override
    public void addResponse(AddResponseRequest request) throws Exception {
        Optional<RecommendationEntity> byId = recommendationService.findById(request.getRecommendationId());
        if (byId.isEmpty())
            throw new RecommendationServiceException("service.recommendation.not.found");
        RecommendationEntity recommendation = byId.get();
        ResponseEntity response = new ResponseEntity();
        response.setResponse(request.getResponse());
        recommendation.setResponse(response);
        recommendation.setStatus(RecommendationStatusEnum.RESPONDED);
        responseRepository.save(response);
    }
}
