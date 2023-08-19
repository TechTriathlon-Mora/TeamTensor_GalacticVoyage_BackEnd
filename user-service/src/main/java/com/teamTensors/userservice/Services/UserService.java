package com.teamTensors.userservice.Services;

import com.teamTensors.userservice.dto.UserDto;
import com.teamTensors.userservice.entity.User;
import com.teamTensors.userservice.repo.UserRepo;
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


    public String updateUser(UserDto userDto){
        if (userRepo.existsById(userDto.getUserId())){
            userRepo.save(modelMapper.map(userDto, User.class));
            return VarList.RSP_SUCCESS;
        } else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<UserDto> getAllEmployee(){
        List<User> employeeList = userRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<UserDto>>(){}.getType());
    }

    public UserDto searchUser(int userId) {
        if (userRepo.existsById(userId)) {
            User user = userRepo.findById(userId).orElse(null);
            return modelMapper.map(user, UserDto.class);
        } else {
            return null;
        }

    }

    public String deleteUser(int userId){
        if (userRepo.existsById(userId)){
            userRepo.deleteById(userId);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
