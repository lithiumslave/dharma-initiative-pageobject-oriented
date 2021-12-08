package be.models.controllers;

import be.models.models.PetModel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetController extends BaseController {

    private RequestSpecification petApi() {
        return apiClient("/pet");
    }

    public Response addPetToStore(PetModel pet) {
        return petApi()
                .when()
                .body(pet)
                .post();
    }

    public Response getAddedPet(String petId) {
        return petApi().when()
                .get("/{petId}", petId);
    }

    public Response updatePetInStore(PetModel pet) {
        return petApi()
                .when()
                .body(pet)
                .put();
    }

    public Response deleteAddedPet(String petId) {
        return petApi().when()
                .delete("/{petId}", petId);
    }

}
