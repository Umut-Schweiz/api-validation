package techproedBatch5;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class GetRequest11 extends TestBase {

    /*
GSON 1) Json formatindaki data lari Java Object lerine dönüstürür (De Serialization)
     2) Java Object lerine Json formatina dönüstürür. (Serialization)
     json --> GSON --> Java (maplere yada listelere cevrilir)   //De Serialization
     Java --> GSON --> json                                     // Serialization
 */

    @Test
    public void get01() {
        Response response = given().
                spec(spec03).
                when().
                get("/2");

        response.prettyPrint();

        HashMap<String,String>  map = response.as(HashMap.class); //De Serialization

        System.out.println(map);
        System.out.println(map.keySet()); //id, completed, title...
        System.out.println(map.values()); //2.0,1.0, false.....


        //completed key degerinin false oldugunu verify ediniz

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(map.get("completed"),false, "False degerinde degil");

        //userId, title ve id degerlerini verify ediniz.
        softAssert.assertEquals(map.get("userId"),1, "1 degerinde degil");//deger dooble a cevrildigi icin
        softAssert.assertEquals(map.get("title"),"quis ut nam facilis et officia qui", "False degerinde degil");
        softAssert.assertEquals(map.get("id"),2, "2 degerinde degil");


        softAssert.assertAll();

        //Map objesini json formatina cevirme
        Gson gson= new Gson();
        System.out.println(gson.toJson(map));



    }
}
