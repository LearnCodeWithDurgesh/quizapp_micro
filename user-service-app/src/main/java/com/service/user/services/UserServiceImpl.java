package com.service.user.services;

import com.service.user.entities.User;
import com.service.user.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{

    private final UserRepo userRepository;


    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(Integer userId, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Update fields
            user.setUserName(updatedUser.getUserName());
            user.setUserEmail(updatedUser.getUserEmail());
            user.setUserPassword(updatedUser.getUserPassword());
            user.setUserAbout(updatedUser.getUserAbout());
            // Update other fields as needed
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }
}
