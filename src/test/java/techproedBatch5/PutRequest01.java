package techproedBatch5;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import static io.restassured.RestAssured.*;
public class PutRequest01 extends  TestBase{
    /*
    var olan datayi comple degistiriyoruz
    put yapmak icin body ve content type ve endpoint e ihityacımız var.
    autorization istenirse girmeliyiz.
     */
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
    }

}
