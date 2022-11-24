package com.iranLabs.assignment.persistence.reposiroty;

import com.iranLabs.assignment.business.serviceEnums.RecommendationStatusEnum;
import com.iranLabs.assignment.business.serviceEnums.RoleEnum;
import com.iranLabs.assignment.persistence.entity.RecommendationEntity;
import com.iranLabs.assignment.persistence.entity.UserEntity;
import com.iranLabs.assignment.persistence.repository.RecommendationRepository;
import com.iranLabs.assignment.persistence.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/24/2022
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecommendationRepositoryTest {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UserRepository userRepository;

    UserEntity user1 ;

    @BeforeEach
    public void prepare() {
        UserEntity user = new UserEntity();
        user.setUsername("hanieh");
        user.setRole(RoleEnum.USER);
        user1 =  userRepository.save(user);
    }

    @AfterEach
    public void delete_user() {
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void should_find_no_recommendation() {
        List<RecommendationEntity> recommendations = recommendationRepository.findAll();
        assertEquals(recommendations.size(), 0);
    }

    @Test
    @Order(2)
    public void should_save_recommendation() {

        RecommendationEntity entity = new RecommendationEntity();
        entity.setRecommendation("this is for test");
        entity.setUser(user1);
        entity = recommendationRepository.save(entity);
        assertThat(entity).hasFieldOrPropertyWithValue("id", entity.getId());
    }

    @Test
    @Order(3)
    public void allow_add_recommendation() {
        boolean exists = recommendationRepository.existsByUserIdAndStatus(user1.getId(), RecommendationStatusEnum.WAITING);
        assertFalse(exists);
    }
}
