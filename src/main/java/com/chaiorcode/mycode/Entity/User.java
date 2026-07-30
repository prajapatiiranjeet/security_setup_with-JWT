package com.chaiorcode.mycode.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true , nullable = false)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    // Enum ko STRING format me store kar rahe hai taaki DB me readable value rahe (ADMIN/FACULTY/STUDENTS).
    private Role role; // enum: ADMIN, FACULTY, STUDENT
}
