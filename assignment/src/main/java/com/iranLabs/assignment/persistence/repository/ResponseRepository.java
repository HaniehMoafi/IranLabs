package com.iranLabs.assignment.persistence.repository;

import com.iranLabs.assignment.persistence.entity.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
@Repository
public interface ResponseRepository extends JpaRepository<ResponseEntity, Integer> {
}
