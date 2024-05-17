package com.service.user.repositories;

import com.service.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User , Integer> {
    Optional<User> findByUserEmail(String userEmail);

}
