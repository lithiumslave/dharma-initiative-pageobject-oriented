package be.models.controllers;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseController {

    public RequestSpecification apiClient(String basePath) {
        return given()
                .baseUri("https://petstore.swagger.io/v2")
                .filter(new AllureRestAssured())
                .basePath(basePath)
                .contentType(ContentType.JSON);
    }
}
