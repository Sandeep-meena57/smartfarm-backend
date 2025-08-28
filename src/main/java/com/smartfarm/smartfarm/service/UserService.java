package com.smartfarm.smartfarm.service;

import com.smartfarm.smartfarm.entity.User;
import com.smartfarm.smartfarm.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {

        return userRepo.findByEmail(email);
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found "));
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
//        user.setRole(updatedUser.getRole());
//        user.setPassword(updatedUser.getPassword());
//        user.setLocation(updatedUser.getLocation());
//        user.setSoilType(updatedUser.getSoilType());
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}
