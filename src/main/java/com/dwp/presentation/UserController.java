package com.dwp.presentation;

import com.dwp.data.model.User;
import com.dwp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Api
@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> getUsersByCityAndDistanceFromCity(
            @RequestParam(value = "city") @NotNull @Size(min = 2) String city,
            @RequestParam(value = "lat") @NotNull @Max(90) @Min(-90) double latitude,
            @RequestParam(value = "long") @NotNull @Max(180) @Min(-180) double longitude,
            @RequestParam(value = "dist") @NotNull @Max(7926) @Min(0) double distanceInMiles) {
        return userService.getUsersByDistanceFromCoordinate(city, latitude, longitude, distanceInMiles);
    }
}
