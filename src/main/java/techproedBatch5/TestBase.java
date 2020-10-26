package techproedBatch5;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import org.json.JSONObject;
import org.junit.Before;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
public class TestBase {
    protected RequestSpecification spec01; //cocuklarin  hepsi kullanabilsin diye
    protected RequestSpecification spec02;
    protected RequestSpecification spec03;
    protected JSONObject jsonBookingDatesBody;
    protected JSONObject jsonRequestBody;
    protected  Map<String, String> bookingDatesMap;
    protected  Map <String, Object> requestBodyMap;
    @Before
    public void setUp01(){
        spec01=  new  RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
        //Uri olusturduk ve spec01 icerisine depoladik.
        // Amacimiz kod tekrarindan kurtulma ve guncellemeleri daha hizli yapabilmek.
    }
    @Before
    public void setUp02(){
        spec02 = new  RequestSpecBuilder().
                setBaseUri("http://dummy.restapiexample.com/api/v1/employees").
                build();
    }
    @Before
    public void setUp03(){
        spec03 = new  RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").
                build();
    }
    protected Response createRequestBodyByJsonObjectClass(){
        //  JSONObject jsonBookingDatesBody; Post request 2a için bu satırı kullandık
        //  JSONObject jsonRequestBody;

        jsonBookingDatesBody   = new JSONObject();
        jsonBookingDatesBody.put("checkin","2020-05-02");
        jsonBookingDatesBody.put("checkout","2020-05-05");
        jsonRequestBody  = new JSONObject();
        jsonRequestBody.put("firstname","Hasan");
        jsonRequestBody.put("lastname","Kara");
        jsonRequestBody.put("totalprice",123);
        jsonRequestBody.put("depositpaid",true);
        jsonRequestBody.put("bookingdates",jsonBookingDatesBody);
        jsonRequestBody.put("additionalneeds","Wifi");
        Response response= given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                body(jsonRequestBody.toString()). //JSonObject class ın body methodu string kabul ettigi icin bu kısmi stringe cevrilir.
                when().
                post("/booking");
        return response;
    }
    protected  Response createRequestBodyByMap(){
        //  Map<String, String> bookingDatesMap; //post request 3a için bu satırı kullandık.
        //   Map <String, Object> requestBodyMap;
        bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin","2020-05-02");
        bookingDatesMap.put("checkout","2020-05-05");
        requestBodyMap= new HashMap<>();
        requestBodyMap.put("firstname","Hasan");
        requestBodyMap.put("lastname","Kara");
        requestBodyMap.put("totalprice",123);
        requestBodyMap.put("depositpaid",true);
        requestBodyMap.put("bookingdates",bookingDatesMap);
        requestBodyMap.put("additionalneeds","Wifi");
        Response response =given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                body(requestBodyMap).
                when().
                post("/booking");
        return response;
    }
}
