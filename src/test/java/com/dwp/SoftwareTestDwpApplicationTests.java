package com.dwp;

import com.dwp.data.UserRepositoryImpl;
import com.dwp.exception.ApiError;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class SoftwareTestDwpApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepositoryImpl repository;

    @LocalServerPort
    int port;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost/api/users";
        RestAssured.port = 8080;
    }

    private static final String URL_PREFIX = "http://localhost:8080/api/users";

    private RequestSpecification givenAuth() {
        return RestAssured.given()
                .auth().preemptive()
                .basic("user", "userPass");
    }

    @Test
    public void givenValidAPIEndPoint_WhenGetUsersByCityAndDistanceFromCity_Returns200() throws Exception {

        mvc.perform(get("http://localhost:8080/api/users?city=London&lat=51.5074&long=0.1278&dist=50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenHttpRequestMethodNotSupported_thenMethodNotAllowed() {
        final Response response = givenAuth().delete(URL_PREFIX + "?city=London&lat=51.5074&long=0.1278&dist=50");
        final ApiError error = response.as(ApiError.class);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, error.getStatus());
        assertEquals(1, error.getErrors().size());
        assertTrue(error.getErrors().get(0).contains("Supported methods are"));
    }


    @Test
    public void whenSendInvalidQueryParam_thenConstraintViolationException() {
        final Response response = givenAuth().body("").get(URL_PREFIX + "?city=London&lat=51.5074&long=0.1278&dist=10000");
        final ApiError error = response.as(ApiError.class);

        assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
        assertEquals(1, error.getErrors().size());
        assertTrue(error.getErrors().get(0).contains("must be less than or equal"));
        System.out.println(response.asString());
    }
}
