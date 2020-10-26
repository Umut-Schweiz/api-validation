package techproedBatch5;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
public class PostRequest04 extends TestBase {
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
           POJO: Plain Old Java Object
           POJO: Json formatindaki data yi Class lara cevirme islemine denir.
           http://www.jsonschema2pojo.org/ adresine gidilir
           Data parantezden diğer paranteze kopyalanıp siteye tasinir.
           sol tarafta class name--> Booking  target --> java  source type -->Json olacak
           daha sonra preview ile class olusturulacak.
           Keylerin hepsi variable olarak tanimlandi.
*/
    @Test
    public void post01(){
        BookingDates bookingDates =new BookingDates("2020-05-02","2020-05-05");
        Booking booking =new Booking("Hasan","Kara",123,true,bookingDates,"Wifi");
        Response response =given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                body(booking).
                when().
                post("/booking");
        response.prettyPrint();

        response.then().assertThat().
                statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert =new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("booking.firstname"),booking.getFirstname());
        softAssert.assertEquals(jsonPath.getString("booking.lastname"),booking.getLastname());
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"),booking.getTotalprice());
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"),booking.getDepositpaid());
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"),booking.getBookingdates().getCheckin());
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"),booking.getBookingdates().getCheckout());
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"),booking.getAdditionalneeds());
        softAssert.assertAll();
    }
}