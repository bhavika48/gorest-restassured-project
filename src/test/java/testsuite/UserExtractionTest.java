package testsuite;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import testbase.TestBase;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {
    static ValidatableResponse response;

    @Before
    public void setup() {
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().log().all()
                .statusCode(200);
    }

    //  Extract the All Ids
    @Test
    public void test001() {
        List<Integer> id = response.extract().path("id");
        System.out.println("All Ids : " + id);
    }


    // Extract the all Names
    @Test
    public void test002() {
        List<String> storeNames = response.extract().jsonPath().getList("name");
        System.out.println("All storeNames  : " + storeNames);
    }

    // Extract the name of 5th object
    @Test
    public void test003() {
        String storeName5 = response.extract().path("name[4]");
        System.out.println("The name of 5th object : " + storeName5);
    }

    // Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> status = response.extract().path("findall{it.status==inactive}");
        System.out.println("All object whose status is inactive : " + status);
    }

    // Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> statusActive = response.extract().path("findall{it.status==active}.gender");
        System.out.println("All object whose status is inactive : " + statusActive);
    }

    // Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> genderFemale = response.extract().path("findall{it.gender==female}.name");
        System.out.println("All object whose gender is female : " + genderFemale);
    }

    // Get all the emails of the object where status = status
    @Test
    public void test007() {
        List<String> status = response.extract().path("findall{it.status==status}.emails");
        System.out.println("All object whose gender is female : " + status);
    }

    // Get the ids of the object where gender = male
    @Test
    public void test008Id() {
        List<Integer> allId = response.extract().path("findAll.id{it.gender==male");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of store Ids are : " + allId);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get all the status
    @Test
    public void test0o9() {
        List<String> allStatus = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the products are :" + allStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test0010() {
        List<String> name = response.extract().path("findAll{it.name=='Karthik Dubashi IV'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the object where name is Karthik Dubashi IV : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get gender of id = 5471
    @Test
    public void test0011() {
        List<String> id = response.extract().path("findAll{it.id==5471}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Gender of id is 5471: " + id);
        System.out.println("------------------End of Test---------------------------");
    }

}
