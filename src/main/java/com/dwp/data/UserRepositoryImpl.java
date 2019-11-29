package com.dwp.data;

import com.dwp.data.model.User;
import com.dwp.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public UserRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers() {

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(
                "https://bpdts-test-app.herokuapp.com/users",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });

        return Objects.requireNonNull(responseEntity.getBody());

    }

    @Override
    public List<User> getUsersByCity(String city) {

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(
                "https://bpdts-test-app.herokuapp.com/city/" + city + "/users",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });

        return Objects.requireNonNull(responseEntity.getBody());
    }


    private HttpHeaders getHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }

    public String getUrl(String path) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("bpdts-test-app.herokuapp.com").path(path)
                .build();

        return uriComponents.toString();
    }
}
