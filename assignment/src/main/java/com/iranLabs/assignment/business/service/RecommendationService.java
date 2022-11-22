package com.iranLabs.assignment.business.service;

import com.iranLabs.assignment.business.model.RecommendationDto;
import com.iranLabs.assignment.business.model.request.AddRecommendationRequest;
import com.iranLabs.assignment.persistence.entity.RecommendationEntity;

import java.util.List;
import java.util.Optional;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public interface RecommendationService {


    void addRecommendation(AddRecommendationRequest request) throws Exception;

    List<RecommendationDto> getAll();

    Optional<RecommendationEntity> findById(Integer id);
}
