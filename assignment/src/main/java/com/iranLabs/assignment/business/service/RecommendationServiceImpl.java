package com.iranLabs.assignment.business.service;

import com.iranLabs.assignment.business.model.RecommendationDto;
import com.iranLabs.assignment.business.model.request.AddRecommendationRequest;
import com.iranLabs.assignment.business.serviceEnums.RecommendationStatusEnum;
import com.iranLabs.assignment.exception.RecommendationServiceException;
import com.iranLabs.assignment.persistence.entity.RecommendationEntity;
import com.iranLabs.assignment.persistence.entity.UserEntity;
import com.iranLabs.assignment.persistence.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@Service
public class RecommendationServiceImpl implements RecommendationService {


    private final UserService userService;

    private final RecommendationRepository repository;


    @Autowired
    public RecommendationServiceImpl(UserService userService, RecommendationRepository repository) {
        this.userService = userService;
        this.repository = repository;
    }


    @Override
    public void addRecommendation(AddRecommendationRequest request) throws Exception {

        UserEntity user = null;
        Optional<UserEntity> byUsername = userService.findByUsername(request.getName());
        if (byUsername.isEmpty()) {
            user = userService.addUser(request.getName());
        } else {
            checkIsAllowedToAddRecommendation(byUsername.get().getId());
            user = byUsername.get();
        }
        RecommendationEntity recommendation = new RecommendationEntity();
        recommendation.setRecommendation(request.getRecommendation());
        recommendation.setUser(user);
        repository.save(recommendation);
    }

    @Override
    public List<RecommendationDto> getAll() {
        List<RecommendationDto> dtos = new ArrayList<>();
        List<RecommendationEntity> recommendationEntities = repository.findAll();
        if (!CollectionUtils.isEmpty(recommendationEntities)) {
            translate(recommendationEntities, dtos);
        }
        return dtos;
    }

    @Override
    public Optional<RecommendationEntity> findById(Integer id) {
        return repository.findById(id);
    }

    private void translate(List<RecommendationEntity> recommendationEntities, List<RecommendationDto> dtos) {
        for (RecommendationEntity r : recommendationEntities) {
            RecommendationDto dto = new RecommendationDto();
            dto.setId(r.getId());
            dto.setRecommendation(r.getRecommendation());
            dto.setName(r.getUser().getUsername());
            dto.setResponse(Objects.nonNull(r.getResponse()) ? r.getResponse().getResponse() : "");
            dtos.add(dto);
        }

    }

    private void checkIsAllowedToAddRecommendation(Integer id) throws Exception {
        boolean exists = repository.existsByUserIdAndStatus(id, RecommendationStatusEnum.WAITING);
        if (exists) throw new RecommendationServiceException("service.user.has.not.allowed.add.recommendation");

    }
}
