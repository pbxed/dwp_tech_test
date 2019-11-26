package com.dwp.presentation;

import com.dwp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users/{city}/{distance}")
    public String getUsersByCityAndDistanceFromCity(@PathVariable(value = "city") String city, @PathVariable(value = "distance") float distance) {
        return userService.getUsersByCityAndDistanceFromCity;
    }
}
