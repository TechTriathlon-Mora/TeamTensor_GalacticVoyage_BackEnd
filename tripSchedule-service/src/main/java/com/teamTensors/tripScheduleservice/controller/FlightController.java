package com.teamTensors.tripScheduleservice.controller;


import com.teamTensors.tripScheduleservice.Servivce.FlightService;
import com.teamTensors.tripScheduleservice.dto.FlightDto;
import com.teamTensors.tripScheduleservice.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utill.VarList;

@CrossOrigin
@RestController
@RequestMapping("api/v1/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/saveFlight")
    public ResponseEntity saveUser(@RequestBody FlightDto flightDto){
        try{
            String res = flightService.saveFlight(flightDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(flightDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (res.equals("86")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Employee have Registered");
                responseDto.setContent(flightDto);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            } else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("ERROR");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
