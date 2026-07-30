package com.chaiorcode.mycode.Controller;


import com.chaiorcode.mycode.Entity.EntityStudent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    // NOTE:
    // Abhi is controller me endpoints implement nahi kiye gaye.
    // SecurityConfig me /faculty/** route ko ADMIN + FACULTY roles ke liye allowed rakha hai,
    // so future me jo bhi APIs yaha add hongi wo JWT + role checks se secure rahengi.
}
