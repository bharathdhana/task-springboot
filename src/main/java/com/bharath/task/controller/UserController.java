package com.bharath.task.controller;

import com.bharath.task.dto.UserDTO;
import com.bharath.task.entity.User;
import com.bharath.task.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
//        String responseBody = userService.getAllUsers().toString();
//        return new ResponseEntity<>(responseBody, HttpStatus.OK);
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
//        String responseBody = userService.getUserById(id).toString();
//        return new ResponseEntity<>(responseBody, HttpStatus.OK);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        String responseBody = userService.createUser(user);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        String responseBody = userService.updateUser(id, user);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String responseBody = userService.deleteUser(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
