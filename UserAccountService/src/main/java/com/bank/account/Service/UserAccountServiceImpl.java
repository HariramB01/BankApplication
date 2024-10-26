package com.bank.account.Service;


import com.bank.SharedDTO.Response.UserResponse;
import com.bank.account.Entity.User;
import com.bank.account.Repository.UserRepository;
import com.bank.account.mapper.UserAccountMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService{

    private UserRepository userRepository;

    public UserAccountServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> allUsers = new ArrayList<>();
        for(User user : users){
            allUsers.add(UserAccountMapper.convertUserToUserResponse(user));
        }
        return allUsers;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
