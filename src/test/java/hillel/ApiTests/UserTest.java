package hillel.ApiTests;

import be.models.controllers.UserController;
import be.models.models.UserModel;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;

public class UserTest {

    static {
        requestSpecification = new RequestSpecBuilder().log(LogDetail.ALL).build();
        responseSpecification = new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }

    Faker faker = new Faker();
    UserController userController = new UserController();

    /*@Test
    public void createUser() {

        long id = faker.number().randomNumber();
        String username = faker.name().username();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String phone = faker.phoneNumber().phoneNumber();
        long userStatus = faker.number().randomNumber();

        UserModel userTarget = new UserModel(id, username, firstName, lastName, email, password, phone, userStatus);

        Response actualUserResponse = RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .basePath("/user")
                .contentType(ContentType.JSON)
                .when()
                .body(userTarget)
                .post();

        Assert.assertEquals(actualUserResponse.getStatusCode(), 200);
        Assert.assertEquals(actualUserResponse.then().extract().jsonPath().getLong("message"), id);
    }*/

    @Test
    public void createUser() {

        long targetId = faker.number().randomNumber();
        String targetUsername = faker.name().username();
        String targetFirstName = faker.name().firstName();
        String targetLastName = faker.name().lastName();
        String targetEmail = faker.internet().emailAddress();
        String targetPassword = faker.internet().password();
        String targetPhone = faker.phoneNumber().phoneNumber();
        long targetUserStatus = faker.number().randomNumber();

        UserModel targetUser = UserModel.builder()
                .id(targetId)
                .username(targetUsername)
                .firstName(targetFirstName)
                .lastName(targetLastName)
                .email(targetEmail)
                .password(targetPassword)
                .phone(targetPhone)
                .userStatus(targetUserStatus).build();

        Response createdUserResponse = userController.addUser(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);

        Assert.assertEquals(createdUserResponse.then().extract().jsonPath().getLong("message"), targetId);
    }

    @Test
    public void getUser() {
        long targetId = faker.number().randomNumber();
        String targetUsername = faker.name().username();
        String targetFirstName = faker.name().firstName();
        String targetLastName = faker.name().lastName();
        String targetEmail = faker.internet().emailAddress();
        String targetPassword = faker.internet().password();
        String targetPhone = faker.phoneNumber().phoneNumber();
        long targetUserStatus = faker.number().randomNumber();

        UserModel targetUser = UserModel.builder()
                .id(targetId)
                .username(targetUsername)
                .firstName(targetFirstName)
                .lastName(targetLastName)
                .email(targetEmail)
                .password(targetPassword)
                .phone(targetPhone)
                .userStatus(targetUserStatus).build();

        Response createdUserResponse = userController.addUser(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);

        Response actualAddedUserResponse = userController.getUser(targetUsername);
        Assert.assertEquals(actualAddedUserResponse.getStatusCode(), 200);
    }

    @Test
    public void updateAddedUser() {
        long targetId = faker.number().randomNumber();
        String targetUsername = faker.name().username();
        String targetFirstName = faker.name().firstName();
        String targetLastName = faker.name().lastName();
        String targetEmail = faker.internet().emailAddress();
        String targetPassword = faker.internet().password();
        String targetPhone = faker.phoneNumber().phoneNumber();
        long targetUserStatus = faker.number().randomNumber();

        UserModel targetUser = UserModel.builder()
                .id(targetId)
                .username(targetUsername)
                .firstName(targetFirstName)
                .lastName(targetLastName)
                .email(targetEmail)
                .password(targetPassword)
                .phone(targetPhone)
                .userStatus(targetUserStatus).build();

        Response createdUserResponse = userController.addUser(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);

        String updatedFirstName = faker.name().name();
        long updatedId = faker.number().randomNumber();

        targetUser.setFirstName(updatedFirstName);
        targetUser.setId(updatedId);

        Response updatedUserResponse = userController.updateUser(targetUsername, targetUser);
        Assert.assertEquals(updatedUserResponse.getStatusCode(), 200);

        Response actualAddedUserResponse = userController.getUser(targetUsername);
        Assert.assertEquals(actualAddedUserResponse.getStatusCode(), 200);

        UserModel actualAddedUser = actualAddedUserResponse.as(UserModel.class);
        Assert.assertEquals(actualAddedUser, targetUser);
    }

    @Test
    public void deleteAddedUser() {
        long targetId = faker.number().randomNumber();
        String targetUsername = faker.name().username();
        String targetFirstName = faker.name().firstName();
        String targetLastName = faker.name().lastName();
        String targetEmail = faker.internet().emailAddress();
        String targetPassword = faker.internet().password();
        String targetPhone = faker.phoneNumber().phoneNumber();
        long targetUserStatus = faker.number().randomNumber();

        UserModel targetUser = UserModel.builder()
                .id(targetId)
                .username(targetUsername)
                .firstName(targetFirstName)
                .lastName(targetLastName)
                .email(targetEmail)
                .password(targetPassword)
                .phone(targetPhone)
                .userStatus(targetUserStatus).build();

        Response createdUserResponse = userController.addUser(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);

        Response deletedUserResponse = userController.deleteUser(targetUsername);
        Assert.assertEquals(deletedUserResponse.getStatusCode(), 200);

        Response getDeletedUserResponse = userController.getUser(targetUsername);
        Assert.assertEquals(getDeletedUserResponse.getStatusCode(), 404);
    }
}
