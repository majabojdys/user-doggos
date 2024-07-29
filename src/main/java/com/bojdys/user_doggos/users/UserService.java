package com.bojdys.user_doggos.users;

import com.bojdys.user_doggos.users.userclient.UserClient;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    private UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<UserDtoResponse> getUsers(){
        return userClient.getUsers().stream()
                .map(u -> new UserDtoResponse(u.name(), u.username(), u.address().city(), u.company().name()))
                .sorted(Comparator.comparing(u -> u.name()))
                .toList();
    }
}
