package com.demo.security.web.rest;

import com.demo.security.service.UserService;
import com.demo.security.domain.User;
import com.demo.security.web.rest.vm.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("api/users")
public class UserResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody SignUpRequest payload) {
        log.info("creating user {}", payload.getUsername());

        User user = new User(payload.getUsername(), payload.getPassword());
        user.setEmail(payload.getEmail());
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        return ResponseEntity.ok(userService.registerUser(user));
    }


    @GetMapping("{username}")
    public ResponseEntity<?> findUser(@PathVariable("username") String username) {
        log.info("retrieving user {}", username);
        return  userService
                .findByUsername(username)
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new RuntimeException(username + " not found."));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        log.info("retrieving all users");
        return ResponseEntity.ok(userService.findAll());
    }

}
