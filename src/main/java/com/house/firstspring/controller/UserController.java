package com.house.firstspring.controller;

import com.house.firstspring.FirstspringApplication;
import com.house.firstspring.domain.User;
import com.house.firstspring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private Logger logger = LoggerFactory.getLogger(FirstspringApplication.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/all")
//    public List<User> users() {
////        todo return list from UserService
////       todo
////        return userService.findAllUser();
//        return Arrays.asList(new User(1, "Jacek"), new User(3, "Maciek"));
//    }



    @GetMapping()
    public String users(Model model) {
//        todo return list from UserService
//       todo
//        return userService.findAllUser();
        final List<User> users = Arrays.asList(new User(1, "Jacek"), new User(3, "Maciek"));
        model.addAttribute("users", users);
        return "index";
    }


    @GetMapping("/get-user/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        final Optional<User> oneById = userService.getOneById(id);
        if (!oneById.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(oneById.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/get-one")
    public User oneusers() {
        return new User(1, "Jacek");
    }


    //    todo create user
    @RequestMapping(value = "/user-create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Creating User : {}", user);

//        todo check by login
        if (userService.isUserExist(user)) {
            logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    //todo
    @RequestMapping(value = "/user-delete", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        logger.info("Deleting User : {}", user);
        if (userService.isUserExist(user)) {
            userService.deleteUser(user);
            logger.info("User delete");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.error("User doesn't exist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
