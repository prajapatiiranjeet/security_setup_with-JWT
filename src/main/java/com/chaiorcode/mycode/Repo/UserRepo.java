package com.chaiorcode.mycode.Repo;

import com.chaiorcode.mycode.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);

    // findByEmail ka use:
    // 1) login time pe user fetch karne ke liye (id nikalne ke liye)
    // 2) JWT filter time pe email se user load karke roles/authorities set karne ke liye
    // First parameter: Entity class (User)
    // Second parameter: ID type (Long)


}
