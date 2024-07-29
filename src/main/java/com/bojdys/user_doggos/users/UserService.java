package com.bojdys.user_doggos.users;

import com.bojdys.user_doggos.users.doggosclient.DoggoClient;
import com.bojdys.user_doggos.users.userclient.UserClient;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    private UserClient userClient;

    private DoggoClient doggoClient;

    public UserService(UserClient userClient, DoggoClient doggoClient) {
        this.userClient = userClient;
        this.doggoClient = doggoClient;
    }

    public List<UserDtoResponse> getUsers(){
        return userClient.getUsers().stream()
                .map(u -> new UserDtoResponse(u.name(), u.username(), u.address().city(), u.company().name(), doggoClient.getDoggos().message()))
                .sorted(Comparator.comparing(u -> u.name()))
                .toList();
    }
}
