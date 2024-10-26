package com.bank.SharedDTO.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private boolean isActive;
}
