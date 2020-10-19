package techproedBatch5;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import techproedBatch5.TestBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
public class GetRequest13 extends TestBase {
    @Test
    public void get01(){
        Response response =given().
                spec(spec02).
                when().
                get();
        response.prettyPrint();
        // ilk 5 ismin "Tiger Nixon","Garrett Winters","Ashton Cox","Cedric Kelly","Airi Satou", oldugunu verify ediniz
        //1. yol isteninilmeyen tavsiye edilmiz
        JsonPath jsonPath =response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(jsonPath.getString("data[0].employee_name"),"Tiger Nixon");
        softAssert.assertEquals(jsonPath.getString("data[1].employee_name"),"Garrett Winters");
        softAssert.assertEquals(jsonPath.getString("data[2].employee_name"),"Ashton Cox");
        softAssert.assertEquals(jsonPath.getString("data[3].employee_name"),"Cedric Kelly");
        softAssert.assertEquals(jsonPath.getString("data[4].employee_name"),"Airi Satou");
        //2. yol olabilir.
        List<String> isimList = new ArrayList<>();
        isimList.add("Tiger Nixon");
        isimList.add("Garrett Winters");
        isimList.add("Ashton Cox");
        isimList.add("Cedric Kelly");
        isimList.add("Airi Satou");
        for(int i =0;i<isimList.size();i++){
            softAssert.assertEquals(jsonPath.getString("data["+i+"].employee_name"),isimList.get(i));
        }
        //3. yol en iyisi
        List <Map> actuelList = jsonPath.getList("data"); // butun data alinmis oldu
        System.out.println(actuelList);
        Map<Integer,String> expectedMap = new HashMap<>();
        expectedMap.put(0,"Tiger Nixon");
        expectedMap.put(1,"Garrett Winters");
        expectedMap.put(2,"Ashton Cox");
        expectedMap.put(3,"Cedric Kelly");
        expectedMap.put(4,"Airi Satou");
        System.out.println(expectedMap);
        for(int i=0;i<expectedMap.size();i++){
            softAssert.assertEquals(actuelList.get(i).get("employee_name"),expectedMap.get(i));
        }
        softAssert.assertAll();
    }
}
