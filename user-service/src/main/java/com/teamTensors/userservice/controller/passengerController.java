package com.teamTensors.userservice.controller;

import com.teamTensors.userservice.Services.PassengerService;
import com.teamTensors.userservice.dto.PassengerDto;
import com.teamTensors.userservice.dto.ResponseDto;
import com.teamTensors.userservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.VarList;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/passenger")
public class passengerController {
    @Autowired
    private PassengerService passengerService;

    @Autowired
    private ResponseDto responseDto;

    @PostMapping(value = "/savePassenger")
    public ResponseEntity savePassenger(@RequestBody PassengerDto passengerDto){
        try{
            String res = passengerService.savePassenger(passengerDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(passengerDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (res.equals("86")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Employee have Registered");
                responseDto.setContent(passengerDto);
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

    @PutMapping (value = "/updatePassenger")
    public ResponseEntity updatePassenger(@RequestBody PassengerDto passengerDto){
        try{
            String res = passengerService.updatePassenger(passengerDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(passengerDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (res.equals("01")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Not a Registered Employee");
                responseDto.setContent(passengerDto);
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


    @GetMapping("/getPassenger")
    public ResponseEntity getAllUser(){
        try{
            List<UserDto> passengerDtoList = passengerService.getAllPassenger();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Success");
            responseDto.setContent(passengerDtoList);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
        } catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/searchPassenger/{passengerId}")
    public ResponseEntity searchPassenger(@PathVariable int passengerId){
        try{
            PassengerDto passengerDto = passengerService.searchPassenger(passengerId);
            if (passengerDto != null){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(passengerDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("No Employee Available in this Id");
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

    @DeleteMapping("/deletePassenger/{passengerId}")
    public ResponseEntity deletePassenger(@PathVariable int passengerId){
        try{
            String res = passengerService.deletePassenegr(passengerId);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(passengerId);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else {
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("No employee available in this id ");
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
