package hillel.ApiTests;

import be.models.controllers.UserController;
import be.models.models.UserModel;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
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

    @Test
    public void createUser() {

        UserModel targetUser = createRandomUser();

        Response createdUserResponse = userController.addUser(targetUser);

        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);

        Assert.assertEquals(createdUserResponse.then().extract().jsonPath().getLong("message"), targetUser.getId());
    }

    @Test
    public void getUser() {

        UserModel targetUser = createRandomUser();

        Response createdUserResponse = userController.addUser(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);

        Response actualAddedUserResponse = userController.getUser(targetUser.getUsername());
        Assert.assertEquals(actualAddedUserResponse.getStatusCode(), 200);

        UserModel actualAddedUser = actualAddedUserResponse.as(UserModel.class);
        Assert.assertEquals(actualAddedUser, targetUser);
    }

    @Test
    public void updateAddedUser() {

        UserModel targetUser = createRandomUser();

        Response createdUserResponse = userController.addUser(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);

        String updatedFirstName = faker.name().name();
        long updatedId = faker.number().randomNumber();

        targetUser.setFirstName(updatedFirstName);
        targetUser.setId(updatedId);

        Response updatedUserResponse = userController.updateUser(targetUser.getUsername(), targetUser);
        Assert.assertEquals(updatedUserResponse.getStatusCode(), 200);

        Response actualAddedUserResponse = userController.getUser(targetUser.getUsername());
        Assert.assertEquals(actualAddedUserResponse.getStatusCode(), 200);

        UserModel actualAddedUser = actualAddedUserResponse.as(UserModel.class);
        Assert.assertEquals(actualAddedUser, targetUser);
    }

    @Test
    public void deleteAddedUser() {

        UserModel targetUser = createRandomUser();

        Response createdUserResponse = userController.addUser(targetUser);
        Assert.assertEquals(createdUserResponse.getStatusCode(), 200);

        Response deletedUserResponse = userController.deleteUser(targetUser.getUsername());
        Assert.assertEquals(deletedUserResponse.getStatusCode(), 200);

        Response getDeletedUserResponse = userController.getUser(targetUser.getUsername());
        Assert.assertEquals(getDeletedUserResponse.getStatusCode(), 404);
    }

    public UserModel createRandomUser() {
        long targetId = faker.number().randomNumber();
        String targetUsername = faker.name().username();
        String targetFirstName = faker.name().firstName();
        String targetLastName = faker.name().lastName();
        String targetEmail = faker.internet().emailAddress();
        String targetPassword = faker.internet().password();
        String targetPhone = faker.phoneNumber().phoneNumber();
        long targetUserStatus = faker.number().randomNumber();

        return UserModel.builder()
                .id(targetId)
                .username(targetUsername)
                .firstName(targetFirstName)
                .lastName(targetLastName)
                .email(targetEmail)
                .password(targetPassword)
                .phone(targetPhone)
                .userStatus(targetUserStatus).build();
    }
}
