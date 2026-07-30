package com.chaiorcode.mycode.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EntityStudent {
    @Id
    // Yaha @GeneratedValue nahi hai, iska matlab id ko manually set karna padega jab entity save hogi.
    private Long id;

    private String username;

    private String password;
}
