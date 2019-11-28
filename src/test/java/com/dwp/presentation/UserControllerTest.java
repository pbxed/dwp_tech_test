package com.dwp.presentation;

import com.dwp.data.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    @Test
    public void whenGetUsersByCityAndDistanceFromCity_ReturnUsers() throws Exception {
        User user = new User(1, "A", "Other", "123@abc.com", "192.168.0.1", 50.0f, 0.0f);
        User user1 = new User(1, "John", "Doe", "1234@abc.com", "192.168.0.2", 50.0f, 0.0f);

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);

        given(userController.getUsersByCityAndDistanceFromCity("London", 51.5074, 0.1278, 50)).willReturn(users);

        mockMvc.perform(get("http://localhost:8080/api/users?city=London&lat=51.5074&long=0.1278&dist=50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}