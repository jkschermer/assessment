package com.example.assessment.service;

import com.example.assessment.dto.UserDto;
import com.example.assessment.entity.User;
import com.example.assessment.repository.UserRepository;
import com.example.assessment.security.PasswordEncoderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;

    public void createAccount(UserDto userDto) throws NoSuchAlgorithmException {
        User user = new User();
        String hashedPassword = passwordEncoderConfig.passwordEncoder().encode(userDto.getPassword());
        user.setPassword(hashedPassword);
        user.setUsername(userDto.getUsername());
        userRepository.save(user);
    }

    public void loginAccount(UserDto userDto) throws Exception {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        User user = userRepository.findByUsername(username);
        String hashedPassword = passwordEncoderConfig.passwordEncoder().encode(password);

        if (user != null && passwordEncoderConfig.passwordEncoder().matches(userDto.getPassword(), hashedPassword)) {
        } else {
            throw new Exception("Invalid username or password");
        }
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}