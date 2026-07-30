package com.chaiorcode.mycode.DTO;

import com.chaiorcode.mycode.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NonNull
    @NotBlank
    private String password;


    @NotNull
    // NOTE:
    // Role field request me aa sakta hai, but AuthService register methods role ko endpoints ke basis pe force karte hai.
    // Example: /register-admin => Role.ADMIN, /register-student => Role.STUDENTS
    private Role role;
}
