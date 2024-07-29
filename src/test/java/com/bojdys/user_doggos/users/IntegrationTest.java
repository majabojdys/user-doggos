package com.bojdys.user_doggos.users;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected RestTemplate restTemplate;

    @LocalServerPort
    protected int randomServerPort;

    protected MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp(){
        mockServer= MockRestServiceServer.createServer(restTemplate);
    }

    protected String getLocalHost(){
        return "http://localhost:" + randomServerPort;
    }

}
