package com.teamTensors.userservice.Services;

import com.teamTensors.userservice.dto.UserDto;
import com.teamTensors.userservice.entity.User;
import com.teamTensors.userservice.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.VarList;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveUser(UserDto userDto){
        if (userRepo.existsById(userDto.getUserId())){
            return VarList.RSP_DUPLICATED;
        }else{
            userRepo.save(modelMapper.map(userDto, User.class));
            return VarList.RSP_SUCCESS;
        }
    }
}
