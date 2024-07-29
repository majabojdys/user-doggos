package com.bojdys.user_doggos.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class UserIntegrationTest extends IntegrationTest{

    @Test
    public void getUsersIntegrationTest(){
        //given
        String doggoUrl = "https://dog.ceo/api/breeds/image/random";
        String doggoResponse = """
                {
                    "message": "https://images.dog.ceo/breeds/kombai/Kombai-indian-Dog.jpg"
                }
                """;
        String userUrl = "https://jsonplaceholder.typicode.com/users";
        String userResponse = """
                [
                    {
                        "name": "Leanne Graham",
                        "username": "Bret",
                        "address": {
                            "city": "Gwenborough"
                            },
                        "company": {
                            "name": "Romaguera-Crona"
                        }
                    },
                    {
                        "name": "Ervin Howell",
                        "username": "Antonette",
                        "address": {
                            "city": "Wisokyburgh"
                            },
                        "company": {
                            "name": "Deckow-Crist"
                        }
                    }
                ]
                """;
        mockServer.expect(requestTo(userUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(userResponse, MediaType.APPLICATION_JSON));
        mockServer.expect(ExpectedCount.twice(), requestTo(doggoUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(doggoResponse, MediaType.APPLICATION_JSON));

        //when
        ResponseEntity<UserDtoResponse[]> result = testRestTemplate.getForEntity(getLocalHost() + "/users", UserDtoResponse[].class);


        //then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        UserDtoResponse[] resultBody = result.getBody();

        Assertions.assertEquals("Ervin Howell", resultBody[0].name());
        Assertions.assertEquals("Antonette", resultBody[0].username());
        Assertions.assertEquals("Wisokyburgh", resultBody[0].city());
        Assertions.assertEquals("Deckow-Crist", resultBody[0].company());
        Assertions.assertEquals("https://images.dog.ceo/breeds/kombai/Kombai-indian-Dog.jpg", resultBody[0].dogAvatarUrl());

        Assertions.assertEquals("Leanne Graham", resultBody[1].name());
        Assertions.assertEquals("Bret", resultBody[1].username());
        Assertions.assertEquals("Gwenborough", resultBody[1].city());
        Assertions.assertEquals("Romaguera-Crona", resultBody[1].company());
        Assertions.assertEquals("https://images.dog.ceo/breeds/kombai/Kombai-indian-Dog.jpg", resultBody[1].dogAvatarUrl());


    }
}
