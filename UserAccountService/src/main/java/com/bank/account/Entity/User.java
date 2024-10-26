package com.bank.account.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Column(nullable = false)
    @NotBlank(message = "First name is mandatory")
    private String firstname;
    @Column(nullable = false)
    @NotBlank(message = "Last name is mandatory")
    private String lastname;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Column(nullable = false)
    private boolean isActive = true;


}
