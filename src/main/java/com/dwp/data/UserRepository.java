package com.dwp.data;

import org.apache.catalina.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsersByCityAndDistanceFromCity();
}
