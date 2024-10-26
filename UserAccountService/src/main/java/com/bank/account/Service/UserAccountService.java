package com.bank.account.Service;

import com.bank.SharedDTO.Response.UserResponse;
import com.bank.account.Entity.User;

import java.util.List;

public interface UserAccountService {
    User createUser(User user);

    List<UserResponse> getAllUsers();

    User getUserById(Long id);

    boolean deleteUserById(Long id);
}
