package techproedBatch5;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
public class PostRequest03a extends  TestBase{
    /*  POST Scenario:
               Content type Json olsun  (Content Type demektir.)
               When
               POST request yolladigimda
            1) https://restful-booker.herokuapp.com/booking
           2) Request Body
           {
               "firstname": "Hasan",
                 "lastname": "Kara",
                 "totalprice": 123,
                 "depositpaid": true,
                "bookingdates": {
               "checkin": "2020-05-02",
               "checkout": "2020-05-05"
           },
               "additionalneeds": "Wifi"
           }
           Then
           Status Code 200 olmali
           Response Body, Request Body ile ayni olduğunu verfy ediniz
*/
    @Test
    public void post01(){
        Response response =createRequestBodyByMap();
        response.prettyPrint();
        //status code 200 olmali
        response.
                then().
                assertThat().
                statusCode(200); //hard assrtion
        //JsonPath kullanarak assertion
        JsonPath jsonPath =response.jsonPath();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(jsonPath.getString("booking.firstname"),"Hasan");
        softAssert.assertEquals(jsonPath.getString("booking.lastname"),"Kara");
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"),123);
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"),true);
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"),"2020-05-02");
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"),"2020-05-05");
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"),"Wifi");
        softAssert.assertAll();
    }
}
