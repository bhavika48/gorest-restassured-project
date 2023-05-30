package crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import testbase.TestBase;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    int userId;
    @Before
    public void setUp() {
        RestAssured.basePath = "/public/v2/users";

    }
    @Test
    public void test001() {
        UserPojo userPojo= new UserPojo();
        userPojo.setName("Sachin");
        userPojo.setEmail("sahchin"+ TestUtils.getRandomValue() +"@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");

        Response response = given()
                .header("Authorization","Bearer 72d70b64d5532cce2c74a8198603ebe97bf36b9002a3b6addb576cb98809967c")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post();
        response.then().log().all().statusCode(201);
       userId= response.body().jsonPath().getInt("id");

    }

    @Test
    public void test002() {

        Response response = given()
                .when()
                .get("/"+userId);
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
                .patch("/"+userId);
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test004() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/"+userId);
        response.then().log().all().statusCode(200);
    }

}


