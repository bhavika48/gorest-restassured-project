package testsuite;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @Before
    public void setup() {
        response = given()
                .when()
                .get("/posts")
                .then().log().all()
                .statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Ids : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        List<String> totalRecord = response.extract().jsonPath().getList("size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Total number of record : " + totalRecord);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        List<String> record15th = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Body of 15th record : " + record15th);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("user_id of all the records : " + userId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("title of all the records : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void test006() {
        List<String> title = response.extract().path("findAll{ituser_id ==5456}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("title of all the records : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 2671
    @Test
    public void test007() {
        List<String> allRecordsId = response.extract().path("findAll{ituser_id ==2671}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All records whose id is 2671 : " + allRecordsId);
        System.out.println("------------------End of Test---------------------------");
    }

}
