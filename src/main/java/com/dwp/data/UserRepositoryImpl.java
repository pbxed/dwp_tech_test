package com.dwp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserRepositoryImpl {

    private final RestTemplate restTemplate;

    @Autowired
    public UserRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
