package crudtest;

import com.gorest.model.UserPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import testbase.TestBase;

import static io.restassured.RestAssured.given;

public class UserCRUDTest2 extends TestBase {
    int userId;
    static ValidatableResponse response;

    @Before
    public void setUp() {
        RestAssured.basePath = "/users";

    }
    @Test
    public void test001() {
        UserPojo userPojo= new UserPojo();
        userPojo.setName("Sachin");
        userPojo.setEmail("sahchin09@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");

         response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post().then().statusCode(201);

         userId = response.extract().path("id");
         
        System.out.println(userId);


    }

    @Test
    public void test002() {

        Response response = given()
                .when()
                .get("/8922");
        response.then().log().all().statusCode(200);

    }

    @Test
    public void test003() {
        UserPojo userPojo= new UserPojo();
        userPojo.setStatus("inactive");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .patch("/8922");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test004() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/8922");
        response.then().log().all().statusCode(200);
    }

}


