package hillel.ApiTests;

import be.models.manual.Category;
import be.models.manual.PetModel;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class PetStoreTest {
    Faker faker = new Faker();

    @Test
    public void addNewPetToStore() {

        int petId = 12345678;

        String newPet = "{\n" +
                "  \"id\": "+ petId +",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"hillel_new_pet\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(newPet)
                .log().all()
                .post()
                .then()
                .statusCode(200)
                .and().assertThat()
                /*.body("id", equalTo(petId))
                .body("name", equalTo("hillel_new_pet"));*/


    }

    @Test
    public void addNewPetObjToStore() {

        long id = faker.number().randomNumber();
        String name = faker.name().name();

        Category petCategory = new Category(25, "test_category");

        PetModel targetPet = new PetModel(id, name, "happy",
                new String[]{}, petCategory, new ArrayList<>());

        Response actualPetResponse = RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet")
                .contentType(ContentType.JSON)
                .when()
                .body(targetPet)
                .post();

        Assert.assertEquals(actualPetResponse.getStatusCode(), 200);

        PetModel actualPet = actualPetResponse.as(PetModel.class);
        Assert.assertEquals(actualPet, targetPet);
    }
}
