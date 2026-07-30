package com.chaiorcode.mycode.Controller;


import com.chaiorcode.mycode.DTO.CreateUserDto;
import com.chaiorcode.mycode.DTO.LoginDTO;
import com.chaiorcode.mycode.DTO.LoginResponceDTO;
import com.chaiorcode.mycode.DTO.RegisterDTO;
import com.chaiorcode.mycode.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private  final AuthService authService;

    @PostMapping("/register-admin")
    public ResponseEntity<RegisterDTO> registeradmin(@RequestBody CreateUserDto createUserDto){
        // Registration endpoints open hote hai (SecurityConfig me /auth/** permitAll hai).
        // Yaha ADMIN user create hoga, role client se nahi, endpoint decide karta hai.
        return ResponseEntity.status(HttpStatus.OK
       ).body(authService.registerAdmin(createUserDto));
    }

    @PostMapping("/register-student")
    public ResponseEntity<RegisterDTO> registerstudent(@RequestBody CreateUserDto createUserDto){
        // Student register ke liye same DTO use ho raha hai, but service me role STUDENTS force hota hai.
        return ResponseEntity.status(HttpStatus.OK
        ).body(authService.registerStudent(createUserDto));
    }

    @PostMapping("/register-faculty")
    public ResponseEntity<RegisterDTO> registerfaculty(@RequestBody CreateUserDto createUserDto){
        // Faculty register endpoint: role FACULTY set hoga.
        return ResponseEntity.status(HttpStatus.OK
        ).body(authService.registerFaculty(createUserDto));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponceDTO> login(@RequestBody LoginDTO loginDTO){
             // Login ka main output JWT token hai.
             // Client is token ko next requests me Authorization header me Bearer token ke form me bhejega.
             return ResponseEntity.status(HttpStatus.OK).body((authService.login(loginDTO)));
    }

}
