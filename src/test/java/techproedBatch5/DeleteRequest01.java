package techproedBatch5;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
public class DeleteRequest01 extends  TestBase{
    //sadece endpoint gerekli.
    @Test
    public void delete01() {
        //get islemi
        Response responseGet =given().
                spec(spec03).
                when().
                get("/198");
        responseGet.prettyPrint();
        //delete islemi
        Response responseDel =given().
                spec(spec03).
                when().
                delete("/198");
        responseDel.prettyPrint();
        //responseDel yazdirildiginda  "Not Found" cevabi gelirse  status code 404 ile test edilir. Eger bos bir
        // satir donerse Ststus code 200 ile test edilir.
        responseDel.then().assertThat().statusCode(200);
        // Hard assert
        assertTrue(responseDel.getBody().asString().contains(""));
        //softAssertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(responseDel.getBody().asString().contains(""));
        softAssert.assertAll();
    }
}
