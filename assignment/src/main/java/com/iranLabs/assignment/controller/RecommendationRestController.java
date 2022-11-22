package com.iranLabs.assignment.controller;

import com.iranLabs.assignment.business.model.request.AddRecommendationRequest;
import com.iranLabs.assignment.business.model.response.BaseResponse;
import com.iranLabs.assignment.business.model.response.GetAllRecommendation;
import com.iranLabs.assignment.business.service.RecommendationService;
import com.iranLabs.assignment.util.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@RestController
@RequestMapping("/recommendation")
public class RecommendationRestController {


    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationRestController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }


    @PostMapping("/add")
    public ResponseEntity<BaseResponse> add(@Valid @RequestBody AddRecommendationRequest request) throws Exception {
        BaseResponse response = new BaseResponse();
        recommendationService.addRecommendation(request);
        response.setMessage(MessageConstant.SUCCESSFUL_SAVE_RECOMMENDATION);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<GetAllRecommendation> getAll()  {
        GetAllRecommendation response = new GetAllRecommendation();
        response.setRecommendations(recommendationService.getAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
