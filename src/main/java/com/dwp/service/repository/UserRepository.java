package com.dwp.service.repository;


import com.dwp.data.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();

    List<User> getUsersByCity(String city);
}
