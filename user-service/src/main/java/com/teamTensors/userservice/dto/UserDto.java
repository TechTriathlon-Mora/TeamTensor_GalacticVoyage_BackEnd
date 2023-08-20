package com.teamTensors.userservice.dto;

import com.teamTensors.userservice.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private int userId;
    private String userName;
    private String userAddress;
    private String userNumber;
    private UserRole role;
}
