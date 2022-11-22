package com.iranLabs.assignment.persistence.repository;

import com.iranLabs.assignment.business.serviceEnums.RecommendationStatusEnum;
import com.iranLabs.assignment.persistence.entity.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@Repository
public interface RecommendationRepository extends JpaRepository<RecommendationEntity, Integer> {

    boolean existsByUserIdAndStatus(Integer userId , RecommendationStatusEnum status);
}
