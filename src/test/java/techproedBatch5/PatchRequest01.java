package techproedBatch5;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.*;
public class PatchRequest01 extends TestBase {
    //Kismi update icin kullanilir.
    @Test
    public void patch01(){
        Response responseBeforePatch =given().
                spec(spec03).
                when().
                get("/200");
        responseBeforePatch.prettyPrint();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("title","Hasan");
        Response responseAfterPatch =given().
                contentType(ContentType.JSON).
                spec(spec03).
                body(jsonObject.toString()).
                when().
                patch("/200");
        responseAfterPatch.prettyPrint();
        responseAfterPatch.
                then().
                assertThat().
                statusCode(200);
        JsonPath jsonPath=responseAfterPatch.jsonPath();
        //title hard assertion
        assertEquals(jsonObject.get("title"),jsonPath.get("title"));
        // title soft assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(jsonPath.getString("title"),jsonObject.get("title"));
        softAssert.assertAll();
    }
}
