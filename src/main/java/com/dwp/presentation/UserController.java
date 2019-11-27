package com.dwp.presentation;

import com.dwp.data.model.User;
import com.dwp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> getUsersByCityAndDistanceFromCity(
            @RequestParam(value = "city") String city,
            @RequestParam(value = "lat") double latitude,
            @RequestParam(value = "long") double longitude,
            @RequestParam(value = "dist") double distanceInMiles) {
        return userService.getUsersByDistanceFromCoordinate(city, latitude, longitude, distanceInMiles);
    }
}
