package com.bank.account.Controller;


import com.bank.SharedDTO.Response.UserResponse;
import com.bank.SharedDTO.dto.UserDTO;
import com.bank.account.Entity.User;
import com.bank.account.Repository.UserRepository;
import com.bank.account.Service.UserAccountService;
import com.bank.account.exception.MethodArgumentNotValidException;
import com.bank.account.mapper.UserAccountMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAccountController {

    private UserRepository userRepository;
    private UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserRepository userRepository, UserAccountService userAccountService){
        this.userRepository = userRepository;
        this.userAccountService = userAccountService;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO){
        logger.debug("UserDTO request received perfectly");
        try{
            User user = UserAccountMapper.convertUserDTOMapper(userDTO);
            userAccountService.createUser(user);
            logger.info("User created");
            return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");
        } catch (MethodArgumentNotValidException e) {
            logger.error("Validation failed during user registration: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        try {
            List<UserResponse> allUsers = userAccountService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(allUsers);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        try {
            User user = userAccountService.getUserById(id);
            if (user != null) {
                UserResponse userResponse = UserAccountMapper.convertUserToUserResponse(user);
                return ResponseEntity.status(HttpStatus.OK).body(userResponse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error retrieving user with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            if (userAccountService.deleteUserById(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Deleted user with ID " + id + " successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }

}
