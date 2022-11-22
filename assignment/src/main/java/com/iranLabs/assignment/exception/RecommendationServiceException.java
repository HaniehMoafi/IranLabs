package com.iranLabs.assignment.exception;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public class RecommendationServiceException extends Exception{



    public RecommendationServiceException() {
    }

    public RecommendationServiceException( String message) {
       super(message);
    }
}
