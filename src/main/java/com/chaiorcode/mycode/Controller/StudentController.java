package com.chaiorcode.mycode.Controller;

import com.chaiorcode.mycode.Entity.EntityStudent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    // NOTE:
    // Abhi is controller me endpoints implement nahi kiye gaye.
    // SecurityConfig me /students/** route ko ADMIN + STUDENT roles ke liye allowed rakha hai,
    // so future me jab bhi yaha APIs add hongi, ye already role-based protected rahengi.
}
