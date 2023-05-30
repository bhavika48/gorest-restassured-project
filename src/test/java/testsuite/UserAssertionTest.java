package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import testbase.TestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @Before
    public void setup() {
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().log().all()
                .statusCode(200);
    }

    // Verify the if the total record is 20

    @Test
    public void test001() {
        response.body("size", equalTo(20));
    }

    // Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002() {
        //response.body("findAll{it.id==5487}",hasItem("Hamsini Trivedi")) ;
        //response.body("findAll{it.id==2223251}",hasItem(hasEntry("name","Gangesh Nair DDS"))) ;
        response.body("findAll{it.id==2223232}.name", hasItem("Msgr. Deeptimoyee Kaniyar"));
    }

    // Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {
        response.body("name", hasItem("Subhashini Talwar"));
    }

    // Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan  Guha, Karthik Dubashi IV)
    @Test
    public void test004() {
        response.body("name", hasItem("Mrs. Menaka Bharadwaj, Msgr. Bodhan  Guha, Karthik Dubashi IV"));
    }

    // Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005() {
        response.body("find{it.id==5471}.email", equalTo("adiga_aanjaneya_rep@jast.org"));
    }

    //Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {
        response.body("find{name=='Shanti Bhat'}.status", equalTo("Active"));
    }

    // Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007() {
        response.body("find{name=='Niro Prajapat'}.gender", equalTo("male"));
    }
}
