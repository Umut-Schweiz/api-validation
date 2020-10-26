package techproedBatch5;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.*;
public class PutRequest02 extends  TestBase{
    @Test
    public void put01(){
        Response response =given().
                spec(spec03).
                when().
                get("/200");
        response.prettyPrint();
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("title","Ali");
        jsonObject.put("userId",201);
        jsonObject.put("completed",true);
        Response responseAfterPut =given().
                contentType(ContentType.JSON).
                spec(spec03).
                body(jsonObject.toString()).
                when().
                put("/200");
        responseAfterPut.prettyPrint();
        responseAfterPut.
                then().
                assertThat().
                statusCode(200);
        JsonPath jsonPath=responseAfterPut.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(jsonObject.getBoolean("completed"),jsonPath.get("completed"));
        softAssert.assertEquals(jsonObject.getInt("userId"),jsonPath.get("userId"));
        softAssert.assertEquals(jsonObject.getString("title"),jsonPath.get("title"));
        softAssert.assertAll();
    }
}
