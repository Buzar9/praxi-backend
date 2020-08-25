package com.buzar.praxibackend.controller;

import com.buzar.praxibackend.entity.User;
import com.buzar.praxibackend.repository.UserRepository;
import com.buzar.praxibackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private UserRepository userRepositoryImpl;

    @GetMapping
    public List<User> findAll() {

        return userRepositoryImpl.findAll();
    }

    @GetMapping("{userId}")
    public User findUserById (@PathVariable(value = "userId") int userId) {

        User tempUser = userRepositoryImpl.findByUserId(userId);

        return tempUser;
    }

    @PostMapping
    public void addUser(@RequestBody User tempUser) {

        userServiceImpl.addUser(tempUser);
    }

    @PutMapping("{userId}")
    public String updateUser(@PathVariable int userId,
                           @RequestBody User tempUser) {
        try {
            userServiceImpl.updateUser(userId, tempUser);
        } catch (Exception exc) {
            return "Data doesn't exist";
        }

        return userServiceImpl.updateUser(userId, tempUser);
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable(value = "userId") int userId) {

        userRepositoryImpl.deleteUser(userId);

        return "Deleted user id - " + userId;
    }
}
