package techproedBatch5;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
public class PostRequest01 extends TestBase{
    /*
  Get Request olusturmak icin sadece endpoint gerekiyordu.
  Post Request olusturmak icin gerekenler;
      *1) EndPoint sart
      *2) Request Body yani kaydedilecek olan data
      3) Autorization sart (eger verilmemisse)
      4) Accept type istege bagli
      5) Content Type (icerik tipi)
       İnterview Sorusu: *get ile post request arasindaki farklar nedir?
                         get endpoint gereklidir. autorization her test icin zorunlu olmayabilir.
                         *post request icin endpoint ve body (data) gereklidir.
                         Get var olan data lari gormek icin kullanilir.
                         Post ise olmayan data yi olusturmak icin kullanilir. (yeni data olusturulur.)
        NOTE: SQL de doldurulmasi zorunlu alanlar vardir. not null,primary key, foreign key vb. constrains denir
              API developer  olusturlacak data nin hangi bolumlerini zorunlu kildi ise o kisimlar bos gecilemez.
              Post request olustururken bu kisimlar kesinlik doldurulmali.
              Sweger dokumantasyonunda bu kisimlar acikca yazar.
              Eger bos gecilirse '400 Bad Request' hatasi aliriz.
                *  POST Scenario:
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

        // Tavsiye edilmeyen 1. yol

        String jsonReqBody = "{\n" +
                "\t\t\t \"firstname\": \"Hasan\",\n" +
                "\t\t\t \"lastname\": \"Kara\",\n" +
                "\t\t\t \"totalprice\": 123,\n" +
                "\t\t\t \"depositpaid\": true,\n" +
                "\t\t\t \"bookingdates\": {\n" +
                "\t\t\t \"checkin\": \"2020-05-02\",\n" +
                "\t\t\t \"checkout\": \"2020-05-05\"\n" +
                "\t\t\t },\n" +
                "\t\t\t \"additionalneeds\": \"Wifi\"\n" +
                "\t\t\t }";
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                body(jsonReqBody).
                when().
                post("/booking");

        response.prettyPrint();

        //status code 200 olmali
        response.
                then().
                assertThat().statusCode(200);//hard assertion

        //JsonPath kullanarak assertion
        JsonPath jsonPath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("booking.firstname"), "Hasan" , "firstname Hasan degil");
        softAssert.assertEquals(jsonPath.getString("booking.lastname"),"Kara");
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"),123);
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"),true);
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"),"2020-05-02");
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"),"2020-05-05");
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"),"Wifi");
        softAssert.assertAll();


    }

}