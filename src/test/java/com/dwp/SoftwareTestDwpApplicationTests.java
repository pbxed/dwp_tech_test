package com.dwp;

import com.dwp.data.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SoftwareTestDwpApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepositoryImpl repository;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenValidAPIEndPoint_WhenGetUsersByCityAndDistanceFromCity_Returns200() throws Exception {

        mvc.perform(get("http://localhost:8080/api/users?city=London&lat=51.5074&long=0.1278&dist=50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}
