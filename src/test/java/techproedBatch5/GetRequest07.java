package techproedBatch5;

import io.restassured.response.Response;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.*;

public class GetRequest07 extends TestBase{
    //Firstname "Susan" olanlari getiren Test Case yazalim.
    //URL spec01 from TestBase


    @Test
    public void get01(){

        Response response =given().
                spec(spec01).
                when().
                get("/booking?firstname=Susan&depositpaid=false");

        response.prettyPrint();
        assertTrue(response.getBody().asString().contains("bookingid"));

    }
    @Test
    public void get02(){
        // spec01.queryParam("firstname","Susan");
        // spec01.queryParam("lastname","Ericson");

        spec01.queryParams("firstname","Susan",
                "lastname","Wilson");

        Response response =given().spec(spec01).get("/booking");
        response.prettyPrint();
        assertTrue(response.getBody().asString().contains("bookingid"));
    }





}