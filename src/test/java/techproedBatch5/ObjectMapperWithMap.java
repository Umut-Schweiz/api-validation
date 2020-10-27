package techproedBatch5;
import Utilities.JsonUtil;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
public class ObjectMapperWithMap extends TestBase{
    //Java ile olusturacagimiz objeyi JsonUtil icinde olusturdugumuz convertJavaTOJson methodu ile json a cevirecegiz.
    @Test
    public void javaToJson(){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(101,"Ali");
        map.put(102,"Veli");
        map.put(103,"Can");
        System.out.println(map);//{101=Ali, 102=Veli, 103=Can}
        //Map objesni Json Formatina cevirme ==>Serialization.
        String JsonFormMap= JsonUtil.convertJavaToJson(map);
        System.out.println(JsonFormMap); //{"101":"Ali","102":"Veli","103":"Can"}
    }
    //API dan get methodu ile gelen JSON formatindaki dat yi map e cevirecegiz ve verification yapacagiz.
    @Test
    public void jsonToJava(){
        Response response =given().
                spec(spec01).
                when().
                get("booking/3");
        response.prettyPrint();
        //Object MApper clas ile bu json dat yi  Java map formatina cevirelim
        Map<String,Object> jsonToMapApi = JsonUtil.convertJsonToJava(response.asString(), Map.class);
        System.out.println(jsonToMapApi);
        /*
        1) API dan gelen Json formatini map e cevirdik
        2) Test case den gelen data lari map ile obje yapacagiz.
        3) olusan bu 2 map objeyi verification yapacagiz.
         */
        //2. Adim
        Map<String, Object> jsonToMapTestCase =new HashMap<>();
        jsonToMapTestCase.put("firstname","Sally");
        jsonToMapTestCase.put("lastname","Smith");
        jsonToMapTestCase.put("totalprice",887);
        jsonToMapTestCase.put("depositpaid",true);
        jsonToMapTestCase.put("additionalneeds","Breakfast");

        response.then().assertThat().statusCode(200);

        assertEquals(jsonToMapTestCase.get("firstname"),jsonToMapApi.get("firstname"));
        assertEquals(jsonToMapTestCase.get("lastname"),jsonToMapApi.get("lastname"));
        assertEquals(jsonToMapTestCase.get("totalprice"),jsonToMapApi.get("totalprice"));
        assertEquals(jsonToMapTestCase.get("depositpaid"),jsonToMapApi.get("depositpaid"));
        assertEquals(jsonToMapTestCase.get("additionalneeds"),jsonToMapApi.get("additionalneeds"));
        //Mapler tek boyutlu (Nested Json olmayan) Jsonlar icin  kolay bir yontemdir.


    }
}
