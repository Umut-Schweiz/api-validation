package techproedBatch5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.*;
public class GetRequest04 {
    /*
    Positive Scenario:
       1) When I send a GET Request to
        2)    http://dummy.restapiexample.com/api/v1/employees
       3) And Accept type  “application/JSON” olsun
        Then
        4)      HTTP Status Code 200 olsun
        5) And Response'in format "application/JSON" olsun
        6 ) And 24 employees olsun
       7) And "Ashton Cox" employee'lerden biri olsun
        8)And 21, 61, ve 23 employee yaslarindan biri olsun
   */
    @Test
    public void get01(){

        Response response = given().
                accept(ContentType.JSON). //1.
                when().
                get("http://dummy.restapiexample.com/api/v1/employees");
        //response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id", Matchers.hasSize(24)).//body den butun data id lerini al ve mathers.hasSize ile
                // kiyasla.
                        body("data.employee_name",Matchers.hasItem("Ashton Cox")).
                //butun isimler bir list icerisine alinir ve icerisinden  aranam deger bakilir.
                        body("data.employee_age",Matchers.hasItems("21","61","23")); //hasItems coklu aramalarda kullanilir.



    }

}