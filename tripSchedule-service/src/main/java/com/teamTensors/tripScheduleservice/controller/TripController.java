package com.teamTensors.tripScheduleservice.controller;

import com.teamTensors.tripScheduleservice.Servivce.TripService;
import com.teamTensors.tripScheduleservice.dto.ResponseDto;
import com.teamTensors.tripScheduleservice.dto.TripDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utill.VarList;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/saveTrip")
    public ResponseEntity saveTrip(@RequestBody TripDto tripDto){
        try{
            String res = tripService.saveTrip(tripDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(tripDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (res.equals("86")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Employee have Registered");
                responseDto.setContent(tripDto);
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

    @PutMapping (value = "/updateTrip")
    public ResponseEntity updateTrip(@RequestBody TripDto tripDto){
        try{
            String res = tripService.updateTrip(tripDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(tripDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (res.equals("01")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Not a Registered Employee");
                responseDto.setContent(tripDto);
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

    @GetMapping("/getTrip")
    public ResponseEntity getAllTrip(){
        try{
            List<TripDto> tripDtoList = tripService.getAllTrip();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Success");
            responseDto.setContent(tripDtoList);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
        } catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
