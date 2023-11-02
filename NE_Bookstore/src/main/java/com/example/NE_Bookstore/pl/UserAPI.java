package com.example.NE_Bookstore.pl;

import com.example.NE_Bookstore.bll.DTOS.UserDTO;
import com.example.NE_Bookstore.bll.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    UserLogic userLogic;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userLogic.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userRequest) {
        return userLogic.registerUser(userRequest);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<UserDTO> confirmUser(@RequestBody UserDTO userRequest) {
        return userLogic.confirmUser(userRequest);
    }

}
