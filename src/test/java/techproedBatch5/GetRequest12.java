package techproedBatch5;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
public class GetRequest12 extends TestBase {
    /*
    GSON 1) Json formatindaki datalari Java Object lerine donusturur. (De Serialization)
         2) Java Object lerini Json formatina donusturur. (Serialization)
     */
    @Test
    public void get01(){
        Response response = given().
                spec(spec03).
                when().
                get();
        response.prettyPrint();
        List<Map<String,String>> listOfMap= response.as(ArrayList.class);
        System.out.println(listOfMap);
        System.out.println(listOfMap.get(0));
        //200 tane id oldugunu verify ediniz.
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(listOfMap.size()==200,"Id sayisi uyusmuyor.");
        //121. elemanin completed degerinin true oldugunu verify ediniz.
        softAssert.assertEquals(listOfMap.get(120).get("completed"),true,"Sonuc true degil");
        // sondan 1 onceki elemanin title degerinin "numquam repellendus a magnam" oldugunu verify ediniz.
        softAssert.assertEquals(listOfMap.get(listOfMap.size()-2).get("title"),"numquam repellendus a magnam",
                "title uyusmuyor.");
        softAssert.assertAll();
    }
}
