package com.teamTensors.tripScheduleservice.Servivce;

import com.teamTensors.tripScheduleservice.Repo.TripRepo;
import com.teamTensors.tripScheduleservice.dto.FlightDto;
import com.teamTensors.tripScheduleservice.dto.TripDto;
import com.teamTensors.tripScheduleservice.entity.Flight;
import com.teamTensors.tripScheduleservice.entity.Trip;
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
public class TripService {

    @Autowired
    private TripRepo tripRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveTrip(TripDto tripDto){
        if (tripRepo.existsById(tripDto.getTripId())){
            return VarList.RSP_DUPLICATED;
        }else{
            tripRepo.save(modelMapper.map(tripDto, Trip.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateTrip(TripDto tripDto){
        if (tripRepo.existsById(tripDto.getTripId())){
            tripRepo.save(modelMapper.map(tripDto, Trip.class));
            return VarList.RSP_SUCCESS;
        } else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<TripDto> getAllTrip(){
        List<Trip> tripList = tripRepo.findAll();
        return modelMapper.map(tripList, new TypeToken<ArrayList<TripDto>>(){}.getType());
    }


    public TripDto searchTrip(int tripId) {
        if (tripRepo.existsById(tripId)) {
            Trip trip = tripRepo.findById(tripId).orElse(null);
            return modelMapper.map(trip, TripDto.class);
        } else {
            return null;
        }
    }

    public String deleteTrip(int tripId){
        if (tripRepo.existsById(tripId)){
            tripRepo.deleteById(tripId);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
