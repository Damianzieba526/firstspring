package com.house.firstspring.controller;

import com.house.firstspring.FirstspringApplication;
import com.house.firstspring.domain.User;
import com.house.firstspring.service.IUserService;
import com.house.firstspring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
    public class UserController {

    private Logger logger = LoggerFactory.getLogger(FirstspringApplication.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all/{id}")
    public List<User> users(@PathVariable Long id) {
        final List<User> users = Arrays.asList(new User(1, "Jacek"), new User(3, "Maciek"));
        return users.stream().filter(user -> id.equals(user.getId())).collect(Collectors.toList());
    }

    @GetMapping("/one")
    public User oneusers() {
        return new User(1, "Jacek");
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Creating User : {}", user);

//        todo check by login
        if (userService.isUserExist(user)) {
            logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
