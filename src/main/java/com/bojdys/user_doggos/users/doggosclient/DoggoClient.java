package com.bojdys.user_doggos.users.doggosclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class DoggoClient {

    private RestTemplate restTemplate;

    public DoggoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Doggo getDoggos(){
        return restTemplate.getForObject("https://dog.ceo/api/breeds/image/random", Doggo.class);
    }

}
