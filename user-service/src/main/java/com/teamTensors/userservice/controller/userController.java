package com.teamTensors.userservice.controller;

import com.teamTensors.userservice.Services.UserService;
import com.teamTensors.userservice.dto.ResponseDto;
import com.teamTensors.userservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.VarList;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseDto responseDto;


    @PostMapping(value = "/saveUser")
    public ResponseEntity saveUser(@RequestBody UserDto userDto){
        try{
            String res = userService.saveUser(userDto);
            if (res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(userDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            } else if (res.equals("86")) {
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Employee have Registered");
                responseDto.setContent(userDto);
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
