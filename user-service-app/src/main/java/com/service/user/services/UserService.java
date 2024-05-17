package com.service.user.services;

import com.service.user.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Integer userId);

    Optional<User> getUserByEmail(String userEmail);

    void deleteUser(Integer userId);

    User updateUser(Integer userId, User updatedUser);
}
