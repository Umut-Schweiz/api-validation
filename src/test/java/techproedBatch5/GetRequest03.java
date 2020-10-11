package techproedBatch5;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest03 {

        /*
        Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yollayalim
         https://restful-booker.herokuapp.com/booking/7
         And Accept type “application/json” mi?
         Then
         HTTP Status Code 200
         And Response format "application/json"
         And firstname "Susan"
         And lastname "Wilson"
         And checkin date "2016-07-13"
         And checkout date "2016-12-15"
         */

    @Test
    public void get01() {
        Response response = given().
                accept("application/json").
                when().
                get(" https://restful-booker.herokuapp.com/booking/7");

        response.prettyPrint();

        //Status code 1. yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("Sally")).
                body("lastname", Matchers.equalTo("Wilson")).
                body("totalprice", Matchers.equalTo(259)).
                body("depositpaid", Matchers.equalTo(true)).
                body("bookingdates.checkin", Matchers.equalTo("2015-03-12")).
                body("bookingdates.checkout", Matchers.equalTo("2020-05-10"));
        // body("additionalneeds",Matchers.equalTo("Breakfast"));


        //Status code 2. yol
        //   assertEquals(200,response.getStatusCode());

    }
}

