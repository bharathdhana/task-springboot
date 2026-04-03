package com.bharath.task.controller;

import com.bharath.task.entity.User;
import com.bharath.task.repository.UserRepository;
import com.bharath.task.service.UserService;
import com.bharath.task.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repository;
    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository repository, UserService service, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body) {
        String email = body.get("email");
//        String password = passwordEncoder.encode(body.get("password"));
        String password = body.get("password");

        var userOptional = repository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User Not Registered", HttpStatus.UNAUTHORIZED);
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>("User Not Registered", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        if (repository.findByEmail(email).isPresent()) {
            return new ResponseEntity<>("Email already Exists", HttpStatus.CONFLICT );
        }

        service.createUser(new User(null, null, email, passwordEncoder.encode(password)));
        return new ResponseEntity<>("Successfully Registered", HttpStatus.CREATED);
    }
}
