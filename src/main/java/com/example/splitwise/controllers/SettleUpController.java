package com.example.splitwise.controllers;


import com.example.splitwise.dtos.*;
import com.example.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SettleUpController {

    private final SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleGroupResponseDto settleGroup(SettleGroupRequestDto requestDto) {

        SettleGroupResponseDto responseDto = new SettleGroupResponseDto();

        try {
            responseDto.setTransactions(settleUpService.settleGroup(requestDto.getGroupId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch(Exception e) {
            System.out.println("Exception from controller: " + e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;

    }

    public SettleUserResponseDto settleUser(SettleUserRequestDto requestDto) {

        SettleUserResponseDto responseDto = new SettleUserResponseDto();

        try {
            responseDto.setTransactions(settleUpService.settleUser(requestDto.getUserId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch(Exception e) {
            System.out.println("Exception from controller: " + e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;

    }

}
