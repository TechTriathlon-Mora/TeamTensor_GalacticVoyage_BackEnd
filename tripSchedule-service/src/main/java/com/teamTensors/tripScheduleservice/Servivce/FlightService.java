package com.teamTensors.tripScheduleservice.Servivce;

import com.teamTensors.tripScheduleservice.Repo.FlightRepo;
import com.teamTensors.tripScheduleservice.dto.FlightDto;
import com.teamTensors.tripScheduleservice.entity.Flight;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utill.VarList;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FlightService {

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveFlight(FlightDto flightDto){
        if (flightRepo.existsById(flightDto.getFlightId())){
            return VarList.RSP_DUPLICATED;
        }else{
            flightRepo.save(modelMapper.map(flightDto, Flight.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateFlight(FlightDto flightDto){
        if (flightRepo.existsById(flightDto.getFlightId())){
            flightRepo.save(modelMapper.map(flightDto, Flight.class));
            return VarList.RSP_SUCCESS;
        } else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<FlightDto> getAllFlight(){
        List<Flight> employeeList = flightRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<FlightDto>>(){}.getType());
    }

}
