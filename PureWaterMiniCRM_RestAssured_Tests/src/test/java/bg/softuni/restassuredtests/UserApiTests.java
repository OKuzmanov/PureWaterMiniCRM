package bg.softuni.restassuredtests;

import bg.softuni.dtos.CustomerDTO;
import bg.softuni.dtos.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserApiTests {

    final String url = "http://localhost:8080";
    final String apiGetAllUsers = "/api/users";
    final String apiGetUserById = "/api/users/";

    @Test
    public void test_getAllUsers() {
        when().request("GET", url + apiGetAllUsers).then().statusCode(200);

        String json = get(url + apiGetAllUsers).asString();

        Gson gson = new GsonBuilder()
                .create();

        UserDTO[] userDTOsList = gson.fromJson(json, UserDTO[].class);

        Assertions.assertTrue(userDTOsList.length > 0);
    }

    @Test
    public void test_getUserById() {
        when().request("GET", url + apiGetAllUsers).then().statusCode(200);

        String json = get(url + apiGetAllUsers).asString();

        Gson gson = new GsonBuilder()
                .create();

        UserDTO[] userDTOsList = gson.fromJson(json, UserDTO[].class);

        if (userDTOsList.length == 0) {
            Assertions.fail("There are no Users in the DB.");
        }

        UserDTO firstUser = userDTOsList[0];
        Long firstUserId = firstUser.getId();

        when().request("GET", url + apiGetUserById + firstUserId).then().statusCode(200);

        get(url + apiGetUserById + firstUserId).then().body("id", equalTo(firstUser.getId().intValue()));
        get(url + apiGetUserById + firstUserId).then().body("username", equalTo(firstUser.getUsername()));
        get(url + apiGetUserById + firstUserId).then().body("firstName", equalTo(firstUser.getFirstName()));
        get(url + apiGetUserById + firstUserId).then().body("password", equalTo(firstUser.getPassword()));
        get(url + apiGetUserById + firstUserId).then().body("lastName", equalTo(firstUser.getLastName()));
        get(url + apiGetUserById + firstUserId).then().body("email", equalTo(firstUser.getEmail()));
    }
}
