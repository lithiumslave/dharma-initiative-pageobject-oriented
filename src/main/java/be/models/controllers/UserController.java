package be.models.controllers;

import be.models.models.UserModel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserController extends BaseController {

    private RequestSpecification userApi() {
        return apiClient("/user");
    }

    public Response addUser(UserModel user) {
        return userApi()
                .when()
                .body(user)
                .post();
    }

    public Response getUser(String username) {
        return userApi()
                .when()
                .get("/{username}", username);
    }

    public Response updateUser(String username, UserModel user) {
        return userApi()
                .when()
                .body(user)
                .put("/{username}", username);
    }

    public Response deleteUser(String username) {
        return userApi()
                .when()
                .delete("/{username}", username);
    }

}
