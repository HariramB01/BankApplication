package com.bank.account.mapper;

import com.bank.SharedDTO.Response.UserResponse;
import com.bank.SharedDTO.dto.UserDTO;
import com.bank.account.Entity.User;

public class UserAccountMapper {
    public static User convertUserDTOMapper(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setActive(userDTO.isActive());
        return user;
    }

    public static UserResponse convertUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setEmail(user.getEmail());
        userResponse.setActive(user.isActive());
        return userResponse;
    }
}
