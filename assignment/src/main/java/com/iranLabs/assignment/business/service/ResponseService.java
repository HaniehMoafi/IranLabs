package com.iranLabs.assignment.business.service;

import com.iranLabs.assignment.business.model.request.AddResponseRequest;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */
public interface ResponseService {
    void addResponse(AddResponseRequest request) throws Exception;
}
