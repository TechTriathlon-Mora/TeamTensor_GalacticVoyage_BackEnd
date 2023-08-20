package com.teamTensors.userservice.entity;


import com.teamTensors.userservice.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String userAddress;
    private String userNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
