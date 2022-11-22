package com.iranLabs.assignment.controller;

import com.iranLabs.assignment.business.model.request.AddResponseRequest;
import com.iranLabs.assignment.business.model.response.BaseResponse;
import com.iranLabs.assignment.business.service.ResponseService;
import com.iranLabs.assignment.util.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Author : Hanieh Moafi
 * @Date : 11/22/2022
 */

@RestController
@RequestMapping("/response")
public class ResponseRestController {


    private final ResponseService responseService;

    @Autowired
    public ResponseRestController(ResponseService responseService) {
        this.responseService = responseService;
    }


    @PostMapping("/add")
    public ResponseEntity<BaseResponse> add(@RequestHeader(name = "Authorization") Map<String, String> headers,
                                            @Valid @RequestBody AddResponseRequest request) throws Exception {
        BaseResponse response = new BaseResponse();
        responseService.addResponse(request);
        response.setMessage(MessageConstant.SUCCESSFUL_SAVE_RESPONSE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
