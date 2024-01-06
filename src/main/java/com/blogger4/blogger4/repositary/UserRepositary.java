package com.blogger4.blogger4.repositary;

import com.blogger4.blogger4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepositary extends JpaRepository<User,Long> {


    Optional<User>findByEmail(String email);
    Optional<User>findByUsernameOrEmail(String username, String email);
    Optional<User>findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
