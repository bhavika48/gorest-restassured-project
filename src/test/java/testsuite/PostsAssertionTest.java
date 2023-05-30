package testsuite;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import testbase.TestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class PostsAssertionTest extends TestBase {
    static ValidatableResponse response;

    @Before
    public void setup() {
        response = given()
                .when()
                .get("/posts")
                .then().log().all()
                .statusCode(200);
    }

    // Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(25));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test002() {
        response.body("findAll{it.id==2730}.title", hasItem("Ad ipsa coruscus ipsam eos demitto centum"));
    }

    // 3. Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        response.body("user_id", hasItem(5522));
    }

    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("user_id", hasItems(2693, 2684, 2681));
    }

    /*5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
        spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
        Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
        Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
        antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
        cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
        adflicto. Assentator umquam pel."*/
    @Test
    public void test006() {
        response.body("findAll{it.user_id==2678}.body", hasItem("Carus eaque voluptatem. Calcar\n" +
                "    spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.\n" +
                "    Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.\n" +
                "    Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et\n" +
                "    antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus\n" +
                "    cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique\n" +
                "    adflicto. Assentator umquam pel."));

    }
}
