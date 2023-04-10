package com.example.assessment.service;

import com.example.assessment.dto.UserDto;
import com.example.assessment.entity.User;
import com.example.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

import static com.example.assessment.util.PasswordHashing.hashPassword;
import static com.example.assessment.util.PasswordHashing.verifyPassword;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}