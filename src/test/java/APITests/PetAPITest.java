package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.AddPetBody;
import models.RandomPetGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.PetEndpoints.*;
import static utilities.URLConstants.BASE_URL;

public class PetAPITest {

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    private int id;

    @Test(priority=0)
    public void verify_addNewPet_returnsCorrectResults() {
        RandomPetGenerator dataGenerator = new RandomPetGenerator();
        AddPetBody addPet = dataGenerator.generateRandomPet();
        id = Integer.parseInt(addPet.getId());
        given()
                .contentType(ContentType.JSON)
                .body(addPet)
                .post(ADD_PET)
                .then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(Integer.parseInt(addPet.getId())))
                .body("name", equalTo(addPet.getName()))
                .body("status", equalTo(addPet.getStatus()))
                .log().all();
    }

    @Test(priority=1)
    public void verify_getPet_returnsCorrectResults(){
        given().when().get(GET_PET + id)
                .then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(id))
                .log().all();
    }

    @Test(priority=2)
    public void verify_updatePet_returnsCorrectResults(){
        RandomPetGenerator dataGenerator = new RandomPetGenerator();
        AddPetBody addPet = dataGenerator.generateRandomPet();
        addPet.setId(Integer.toString(id));
        addPet.setStatus("pending");
        given()
                .contentType(ContentType.JSON)
                .body(addPet)
                .put(UPDATE_PET)
                .then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("id", equalTo(Integer.parseInt(addPet.getId())))
                .body("status", equalTo(addPet.getStatus()))
                .log().all();
    }

    @Test(priority=3)
    public void verify_deletePet_returnsCorrectResults(){
        given().when().delete(DELETE_PET + id)
                .then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("message", equalTo(Integer.toString(id)))
                .log().all();
    }

    @Test(priority=4)
    public void verify_searchDeletedPet_returnsCorrectResults(){
        given().when().get(GET_PET + id)
                .then().assertThat()
                .statusCode(404)
                .contentType("application/json")
                .body("type", equalTo("error"))
                .body("message", equalTo("Pet not found"))
                .log().all();
    }
}
