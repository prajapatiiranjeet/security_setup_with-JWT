package com.chaiorcode.mycode.Service;


import com.chaiorcode.mycode.DTO.CreateUserDto;
import com.chaiorcode.mycode.DTO.LoginDTO;
import com.chaiorcode.mycode.DTO.LoginResponceDTO;
import com.chaiorcode.mycode.DTO.RegisterDTO;
import com.chaiorcode.mycode.Entity.Role;
import com.chaiorcode.mycode.Entity.User;
import com.chaiorcode.mycode.Repo.UserRepo;
import com.chaiorcode.mycode.security.JwtService;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final  CustomUserDetailsService customUserDetailsService;


 //   ye Users create krne ke liye hai
//*******************************************************************************************************************************
    //yaha se Admin create hoga
    public RegisterDTO registerAdmin(CreateUserDto createUserDto){
        // Yaha naya User object banaya ja raha hai jo DB me save hoga.
        // Controller se jo data aata hai (name/email/password), usko entity me set kar rahe hai.
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());
        // Password ko plain text me store karna dangerous hota hai.
        // Isliye PasswordEncoder (BCrypt) se encode karke hi DB me save kar rahe hai.
        user.setPassword( passwordEncoder.encode(createUserDto.getPassword()));
        // Role client ke input pe depend nahi kar raha. Admin register endpoint ka matlab hi ADMIN user create karna hai.
        user.setRole(Role.ADMIN);


        User saved = userRepo.save(user);

        // in teeno cheezo ko bhejne ke liye "RegisterDTO" bnaya hai wo in teeno return kri huyi cheezo ko carry krega or jaha bhena hai waha bhejega
        return new RegisterDTO(saved.getId() , saved.getName() , saved.getRole());
    }

    //*******************************************************************************************************
//yaha se student create hoga
    public RegisterDTO registerStudent(CreateUserDto createUserDto){
        // Same flow as admin, bas role STUDENTS set ho raha hai.
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());
        user.setPassword( passwordEncoder.encode(createUserDto.getPassword()));
        user.setRole(Role.STUDENTS);


        User saved = userRepo.save(user);

        return new RegisterDTO(saved.getId() , saved.getName() , saved.getRole());
    }

    //*******************************************************************************************************************************************
    //yaha faculty create hogi

    public RegisterDTO registerFaculty(CreateUserDto createUserDto){
        // Faculty registration me bhi password encode + fixed role mapping follow hoti hai.
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());
        user.setPassword( passwordEncoder.encode(createUserDto.getPassword()));
        user.setRole(Role.FACULTY);


        User saved = userRepo.save(user);

        return new RegisterDTO(saved.getId() , saved.getName() , saved.getRole());


    }



    //***************************************************************************************************************************************************************************************
   // yaha se login hoga jwt token se
    public  LoginResponceDTO login(LoginDTO loginDTO) {

        // Yaha Spring Security ka AuthenticationManager username/password validate karta hai.
        // Flow:
        // 1) AuthenticationManager -> UserDetailsService ko call karta hai (CustomUserDetailsService)
        // 2) UserDetailsService DB se user nikalta hai
        // 3) PasswordEncoder se password match hota hai
        // 4) Sab sahi hua to Authentication object return hota hai (authenticated = true)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail() , loginDTO.getPassword())


        );
        // authentication.getName() generally username return karta hai (hamare case me email).
        String email = authentication.getName();
        // Yaha DB se user fetch kar rahe hai taaki response me id bhej sake.
        // (Authentication principal me bhi info ho sakti hai, but yaha simple approach use kiya gaya hai.)
        User user = userRepo.findByEmail(email)
                .orElseThrow();

        Long id = user.getId();

        // JWT token generate kar rahe hai.
        // JWT me subject (sub) = user email set hota hai, aur token sign hota hai secret key se.
        // Ye token client ko milega aur next requests me Authorization header me Bearer token ke form me aayega.
        String jwtToken = jwtService.generatJwttoken((UserDetails) Objects.requireNonNull(authentication.getPrincipal()));

                return new LoginResponceDTO(id , jwtToken);


    }
}
