package com.bojdys.user_doggos.users;

import com.bojdys.user_doggos.users.doggosclient.Doggo;
import com.bojdys.user_doggos.users.doggosclient.DoggoClient;
import com.bojdys.user_doggos.users.userclient.User;
import com.bojdys.user_doggos.users.userclient.UserAddress;
import com.bojdys.user_doggos.users.userclient.UserClient;
import com.bojdys.user_doggos.users.userclient.UserCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class UserServiceTest {

    private UserClient userClient = Mockito.mock(UserClient.class);
    private DoggoClient doggoClient = Mockito.mock(DoggoClient.class);

    private UserService userService = new UserService(userClient, doggoClient);

    @Test
    public void getUsersTest() {
        //given
        UserAddress address = new UserAddress("Gliwice");
        UserCompany company = new UserCompany("xyz");
        Doggo doggo1 = new Doggo("https://images.dog.ceo/breeds/kombai/Kombai-indian-Dog.jpg");
        User user1 = new User("Maja", "Maja2", address, company);
        User user2 = new User("Karolina", "Karolina2", address, company);
        Mockito.when(userClient.getUsers()).thenReturn(List.of(user1, user2));
        Mockito.when(doggoClient.getDoggos()).thenReturn(doggo1);

        //when
        List<UserDtoResponse> result = userService.getUsers();

        //then
        UserDtoResponse userDto1 = new UserDtoResponse(user1.name(), user1.username(), address.city(), company.name(), doggo1.message());
        UserDtoResponse userDto2 = new UserDtoResponse(user2.name(), user2.username(), address.city(), company.name(), doggo1.message());
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(userDto2, result.get(0));
        Assertions.assertEquals(userDto1, result.get(1));
    }
}
