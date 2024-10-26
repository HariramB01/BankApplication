package com.bank.SharedDTO.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private boolean isActive;
}
