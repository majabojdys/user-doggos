package com.bojdys.user_doggos.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserEndpoint {

    private UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDtoResponse> getUsers(){
       return userService.getUsers();
    }

}
