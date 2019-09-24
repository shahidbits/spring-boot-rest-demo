package com.example.springbootin28min.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return service.get();
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {

        User savedUser = service.post(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping(path = "/users/{userId}")
    public User getUserById(@PathVariable int userId) {
        User user = service.getById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found (id: " + userId + ")");
        }
        return user;
    }

    @DeleteMapping(path = "/users/{userId}")
    public User deleteUserById(@PathVariable int userId) {

        User user = service.deleteById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found (id: " + userId + ")");
        }
        return user;

    }
}
