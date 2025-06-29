package com.learnwithiftekhar.spring_jwt_demo.ropository;

import com.learnwithiftekhar.spring_jwt_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
