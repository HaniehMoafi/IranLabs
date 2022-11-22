package com.iranLabs.assignment.util;


import com.iranLabs.assignment.business.model.response.BaseResponse;
import com.iranLabs.assignment.exception.AuthorizationServiceException;
import com.iranLabs.assignment.exception.RecommendationServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author : Hanieh Moafi
 * @Date : 10/7/2022
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BaseResponse response = new BaseResponse();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            response.setMessage(fieldName + " : " + errorMessage);

        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecommendationServiceException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(RecommendationServiceException ex) {
        BaseResponse response = new BaseResponse();
        String expMessage = MessageBundler.getExpMessage(ex.getMessage());
        response.setMessage(expMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(AuthorizationServiceException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(AuthorizationServiceException ex) {
        BaseResponse response = new BaseResponse();
        String expMessage = MessageBundler.getExpMessage(ex.getMessage());
        response.setMessage(expMessage);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
