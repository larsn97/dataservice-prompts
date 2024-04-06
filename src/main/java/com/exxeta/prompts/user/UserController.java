package com.exxeta.prompts.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping(path = "/user")
    public ResponseEntity<User> getUser(@RequestParam(value = "id") Long id) {
        Optional<User> optionalUser = userService.getUserById(id);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path="/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam(value = "username") String username,
                                                      @RequestParam(value = "password") String password) {
        User user = userService.getUserByUsernameAndPassword(username, password);
        Map<String, Object> map = new HashMap<>();

        if(user.getId() != null) {
            map.put("userId", user.getId());
            map.put("isAuthenticated", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("isAuthenticated", false);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
