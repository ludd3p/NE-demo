package com.example.NE_Bookstore.bll;

import com.example.NE_Bookstore.bll.DTOS.UserDTO;
import com.example.NE_Bookstore.dal.Entities.User;
import com.example.NE_Bookstore.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserLogic {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Converter converter;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User u: users) {
            userDTOS.add(converter.convertUserToDTO(u));
        }
        return userDTOS;
    }

    public ResponseEntity<String> registerUser(UserDTO userDTO) {
        try {
            User user = converter.convertDTOToUser(userDTO);
            userRepository.save(user);
            return ResponseEntity.ok().body("Registration successful");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("User already exists");
        }
    }

    public ResponseEntity<UserDTO> confirmUser(UserDTO userDTO) {
        try {
            User user = userRepository.findByUsername(userDTO.getUsername());
            if (userDTO.getPassword().equals(user.getPassword()))
                return ResponseEntity.ok().body(converter.convertUserToDTO(user));
            return ResponseEntity.badRequest().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
