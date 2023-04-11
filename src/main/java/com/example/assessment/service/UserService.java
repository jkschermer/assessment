package com.example.assessment.service;

import com.example.assessment.dto.UserDto;
import com.example.assessment.entity.User;
import com.example.assessment.repository.UserRepository;
import com.example.assessment.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static com.example.assessment.util.PasswordHashing.hashPassword;
import static com.example.assessment.util.PasswordHashing.verifyPassword;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public void createAccount(UserDto userDto) throws NoSuchAlgorithmException {
        User user = new User();
        String hashedPassword = hashPassword(userDto.getPassword());
        user.setPassword(hashedPassword);
        user.setUsername(userDto.getUsername());
        userRepository.save(user);
    }

    public void loginAccount(UserDto userDto) throws Exception {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        User user = userRepository.findByUsername(username);
        String hashedPassword = hashPassword(password);

        if (user != null && verifyPassword(userDto.getPassword(), hashedPassword)) {
        } else {
            throw new Exception("Invalid username or password");
        }
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}