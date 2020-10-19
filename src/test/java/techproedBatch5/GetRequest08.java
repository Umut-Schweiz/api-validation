package techproedBatch5;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
public class GetRequest08 extends TestBase{
    /*
     When I send a GET request to REST API URL
          https://restful-booker.herokuapp.com/booking/5
     Then HTTP Status Code 200 olsun
     And response content type “application/JSON” olsun
     And response body asagidaki gibi olsun;
     {
     "firstname": "Sally",
     "lastname": "Ericsson",
     "totalprice": 111,
     "depositpaid": false,
     "bookingdates": {
                       "checkin": "2017-05-23",
                       "checkout":"2019-07-02" }
     }
    */
    @Test
    public void get01(){
        spec01.pathParam("bookingid",5);
        Response response =given().
                spec(spec01).
                when().
                // get ("/booking/5"); seklinde satir icerisine yazilmasi has karailanmaz.
                        get("/booking/{bookingid}");
        response.prettyPrint();
        JsonPath jsonPath =response.jsonPath();
        jsonPath.getString("firstname");//json objesi ile firstname degerine string e cevirerek ulasmis olduk.
        System.out.println(jsonPath.getString("firstname"));
        System.out.println(jsonPath.getString("lastname"));
        System.out.println(jsonPath.getString("totalprice"));
        System.out.println(jsonPath.getString("depositpaid"));
        System.out.println(jsonPath.getString("bookingdates"));
        System.out.println(jsonPath.getString("bookingdates.checkin"));
        System.out.println(jsonPath.getString("bookingdates.checkout"));
        assertEquals("firstname istenilen gibi degil","Jim",jsonPath.getString("firstname"));
        //1. bolum teknik olmayan kisiler icin verilen hatayi okunabilir hale ceviren mesaj kismi
        //2. bolum olmasini bekledigimiz degeri girdigimiz alan
        //3 bolum gercek degerin oldu yer.
        assertEquals("lastname istenilen gibi degil","Ericsson",jsonPath.getString("lastname"));
        assertEquals("totalprice istenilen gibi degil","744",jsonPath.getString("totalprice"));
        assertEquals("depositpaid istenilen gibi degil","false",jsonPath.getString("depositpaid"));
        assertEquals("bookingdates.checkin istenilen gibi degil","2018-03-15",jsonPath.getString("bookingdates.checkin"));
        assertEquals("bookingdates.checkout istenilen gibi degil","2020-09-27",jsonPath.getString("bookingdates.checkout"));
    }
}