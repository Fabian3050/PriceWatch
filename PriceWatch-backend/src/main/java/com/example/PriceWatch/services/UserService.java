package com.example.PriceWatch.services;

import com.example.PriceWatch.entities.UserEntity;
import com.example.PriceWatch.jwt.JwtAuthenticationFilter;
import com.example.PriceWatch.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    private final PasswordEncoder passwordEncoder;

    //find user by username
    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //find user by id
    public Optional<UserEntity> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    //find all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    //update user
    public UserEntity updateUser(UserEntity user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("User is not authenticated");
        }

        UserEntity existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + user.getId() + " does not exist."));

        String currentUsername = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));

        if (!currentUsername.equals(existingUser.getUsername()) && !isAdmin) {
            throw new SecurityException("Not authorized to update this user");
        }

        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }

        return userRepository.save(existingUser);
    }

    //update user password
    public UserEntity updatePassword(UserEntity user) {
        UserEntity existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + user.getId() + " does not exist."));

        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    //delete user
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteUser(Integer id) throws Exception {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
