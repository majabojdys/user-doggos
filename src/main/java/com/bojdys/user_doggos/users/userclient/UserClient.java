package com.bojdys.user_doggos.users.userclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class UserClient {

    private RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getUsers(){
        User[] users = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", User[].class);
        return Arrays.asList(users);
    }

}
