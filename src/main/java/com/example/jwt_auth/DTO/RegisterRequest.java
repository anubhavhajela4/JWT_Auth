package com.example.jwt_auth.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Please provide a firstname")
    String firstName;
    @NotBlank(message = "Please provide a lastname")
    String lastName;
    @Email(message = "Please provide a valid email address")
    String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;
}
