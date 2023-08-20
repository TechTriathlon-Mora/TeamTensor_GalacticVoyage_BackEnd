package com.teamTensors.userservice.Services;

import com.teamTensors.userservice.dto.PassengerDto;
import com.teamTensors.userservice.dto.UserDto;
import com.teamTensors.userservice.entity.Passenger;
import com.teamTensors.userservice.entity.User;
import com.teamTensors.userservice.repo.PasssengerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.VarList;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PassengerService {
    @Autowired
    private PasssengerRepo passengerRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String savePassenger(PassengerDto passengerDto){
        if (passengerRepo.existsById(passengerDto.getPassengerId())){
            return VarList.RSP_DUPLICATED;
        }else{
            passengerRepo.save(modelMapper.map(passengerDto, Passenger.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updatePassenger(PassengerDto passengerDto){
        if (passengerRepo.existsById(passengerDto.getPassengerId())){
            passengerRepo.save(modelMapper.map(passengerDto, Passenger.class));
            return VarList.RSP_SUCCESS;
        } else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<UserDto> getAllPassenger(){
        List<Passenger> passengerList = passengerRepo.findAll();
        return modelMapper.map(passengerList, new TypeToken<ArrayList<PassengerDto>>(){}
                .getType());

    }

    public PassengerDto searchPassenger(int passengerId) {
        if (passengerRepo.existsById(passengerId)) {
            Passenger passenger = passengerRepo.findById(passengerId).orElse(null);
            return modelMapper.map(passenger, PassengerDto.class);
        } else {
            return null;
        }
    }

    public String deletePassenegr(int passengerId){
        if (passengerRepo.existsById(passengerId)){
            passengerRepo.deleteById(passengerId);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
