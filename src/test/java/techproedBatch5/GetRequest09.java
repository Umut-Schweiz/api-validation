package techproedBatch5;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetRequest09 extends  TestBase{


    @Test
    public void get01(){
        Response response =given().
                spec(spec02).
                when().
                get();
        response.prettyPrint();
        //Json path objesi olusturunuz.

        JsonPath jsonPath = response.jsonPath();

        //Tum employe isimlerini consol a yazdiralim.
        System.out.println(jsonPath.getString("data.employee_name"));
        //System.out.println(jsonPath.getList("data.employee_name"));

        //2. iscinin 'Garrett Winters' oldugunu 'verify' edniz.==> soft test

        assertEquals("isim istenildigi gibi degil", "Garrett Winters",
                    jsonPath.getString("data[1].employee_name"));

         /*Soft assertion  icin 3 adimi takip etmek gerekir
            1)SoftAssert class indan bir obje (softAssert) uretilir
            2)Objeyi kullanarak assertion yapilir.
            3)SoftAssert.assertAll(); ile test bitirilir.
         */
        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals(jsonPath.getString("data[1].employee_name"),"Garrett Winters",
                "isim  istenildigi gibi degil");


        softAssert.assertTrue(jsonPath.getList("data.employee_name").contains("Herrod Chandler"),
                "boyle bir isci ismi yok");


        //24 tane iscinin oldugunu 'verify' ediniz.
        softAssert.assertEquals(jsonPath.getList("data.id").size(),24,"isci sayisi 24 kisi degildir");

        //7. iscini maasinin "137000" oldugunu verify ediniz

        softAssert.assertEquals(jsonPath.getString("data[6].employee_salary"), "137500", "Brielle Williamson maasi 137500 degil");






        softAssert.assertAll();







    }
}
