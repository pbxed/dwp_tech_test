package com.dwp.service;

import com.dwp.data.UserRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepositoryImpl userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void whenGetUsersByDistanceFromCoordinate_ReturnsUsers() {
        Mockito.when(userRepository.getUsersByCity("London")).thenReturn(new ArrayList<>());

        assertThat(new ArrayList<>()).isEqualTo(userService.getUsersByDistanceFromCoordinate("London", 50, 50, 50));

    }
}