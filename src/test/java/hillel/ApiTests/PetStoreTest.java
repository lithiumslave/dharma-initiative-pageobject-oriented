package ApiTests;

import be.models.controllers.PetController;
import be.models.models.PetModel;
import be.models.models.manual.Category;
import be.models.models.manual.Pet;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreTest {

    static {
        requestSpecification = new RequestSpecBuilder().log(LogDetail.ALL).build();
        responseSpecification = new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }

    Faker faker = new Faker();
    PetController petController = new PetController();

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
                .body("id", equalTo(petId))
                .body("name", equalTo("hillel_new_pet"));


    }

    @Test(groups = {"api_tests", "smoke"})
    public void addNewPetObjToStore() {

        long id = faker.number().randomNumber();
        String name = faker.name().name();

        Category petCategory = new Category(25, "test_category");

        Pet targetPet = new Pet(id, name, "happy",
                new String[]{}, petCategory, new ArrayList<>());

        Response actualPetResponse = RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/pet")
                .contentType(ContentType.JSON)
                .when()
                .body(targetPet)
                .post();

        Assert.assertEquals(actualPetResponse.getStatusCode(), 200);

        Pet actualPet = actualPetResponse.as(Pet.class);
        Assert.assertEquals(actualPet, targetPet);
    }

    @Test(groups = {"api_tests"})
    public void addNewPetBuilder() {

        long targetId = faker.number().randomNumber();
        String targetName = faker.name().name();

        PetModel targetPet = PetModel.builder()
                .id(targetId)
                .name(targetName).build();

        Response createdPetResponse = petController.addPetToStore(targetPet);
        Assert.assertEquals(createdPetResponse.getStatusCode(), 200);

        PetModel actualPet = createdPetResponse.as(PetModel.class);
        Assert.assertEquals(actualPet, targetPet);

        Response actualAddedPetResponse = petController.getAddedPet(String.valueOf(targetId));
        Assert.assertEquals(actualAddedPetResponse.getStatusCode(), 200);

        PetModel actualAddedPet = actualAddedPetResponse.as(PetModel.class);
        Assert.assertEquals(actualAddedPet, targetPet);
    }

    @Test(groups = {"api_tests", "smoke"})
    public void updateAddedPet() {

        long targetId = faker.number().randomNumber();
        String targetName = faker.name().name();

        PetModel targetPet = PetModel.builder()
                .id(targetId)
                .name(targetName).build();

        Response createdPetResponse = petController.addPetToStore(targetPet);
        Assert.assertEquals(createdPetResponse.getStatusCode(), 200);

        PetModel actualPet = createdPetResponse.as(PetModel.class);
        Assert.assertEquals(actualPet, targetPet);

        String updatedName = faker.name().name();
        targetPet.setName(updatedName);

        Response updatedPetResponse = petController.updatePetInStore(targetPet);
        Assert.assertEquals(updatedPetResponse.getStatusCode(), 200);

        Response actualAddedPetResponse = petController.getAddedPet(String.valueOf(targetId));
        Assert.assertEquals(actualAddedPetResponse.getStatusCode(), 200);

        PetModel actualAddedPet = actualAddedPetResponse.as(PetModel.class);
        Assert.assertEquals(actualAddedPet, targetPet);

    }

    @Test(groups = {"api_tests"})
    public void deleteAddedPet() {

        long targetId = faker.number().randomNumber();
        String targetName = faker.name().name();

        PetModel targetPet = PetModel.builder()
                .id(targetId)
                .name(targetName).build();

        Response createdPetResponse = petController.addPetToStore(targetPet);
        Assert.assertEquals(createdPetResponse.getStatusCode(), 200);

        Response deletedPetResponse = petController.deleteAddedPet(String.valueOf(targetId));
        Assert.assertEquals(deletedPetResponse.getStatusCode(), 200);

        Response getDeletedPetResponse = petController.getAddedPet(String.valueOf(targetId));
        Assert.assertEquals(getDeletedPetResponse.getStatusCode(), 404);

    }
}
