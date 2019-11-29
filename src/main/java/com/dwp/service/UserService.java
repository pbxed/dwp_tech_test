package com.dwp.service;

import com.dwp.service.repository.UserRepository;
import com.dwp.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersByDistanceFromCoordinate(String city, double latitude, double longitude, double distance) {
        List<User> usersByCity = getUsersByCity(city);
        List<User> usersByDistanceFromCoordinate = getUsersByDistanceFromCoordinate(latitude, longitude, distance);

        usersByCity.addAll(usersByDistanceFromCoordinate);

        return usersByCity;
    }

    private List<User> getUsersByCity(String city) {
        List<User> filteredUserList = new ArrayList<>();
        filteredUserList.addAll(userRepository.getUsersByCity(city));
        return filteredUserList;
    }

    private List<User> getUsersByDistanceFromCoordinate(double latitude, double longitude, double distance) {
        List<User> usersByDistanceFromCoordinate = new ArrayList<>();

        for(User user : userRepository.getUsers()) {
            if(distanceBetweenTwoCoordinates(user.getLatitude(), latitude, user.getLongitude(), longitude) <= distance) {
                usersByDistanceFromCoordinate.add(user);
            }
        }

        return usersByDistanceFromCoordinate;
    }

    private static double distanceBetweenTwoCoordinates(double latitudeUser, double latitudeReferencePoint, double longitudeUser, double longitudeReferencePoint) {

        final int radiusOfEarthInKm = 6371;

        double latDistance = Math.toRadians(latitudeReferencePoint - latitudeUser);
        double lonDistance = Math.toRadians(longitudeReferencePoint - longitudeUser);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitudeUser)) * Math.cos(Math.toRadians(latitudeReferencePoint))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radiusOfEarthInKm * c * 1000 / 1609.344;
    }
}
