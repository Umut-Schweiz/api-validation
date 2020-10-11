package techproedBatch5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class GetRequest01 {

    //Rest-Assured kullnarak API Testing yapacagiz.
    @Test

    public void getMethod01(){

        given().  //restAssured ten gelen bir method.
                when(). //when den sonra --get, post, put, patch, delete-- methodları calistirilir.
                //   get("https://restful-booker.herokuapp.com"). //get te sadece Endpoint kullanilir.
                        get("https://restful-booker.herokuapp.com/booking").
                then(). //dogrulama demek
                assertThat().
                statusCode(200);
    }

    //gete ile aldigimiz data yi console da gormek istiyoruz
    //gelen datayi bir container icerisine alip yazdirmak gerekiyor. Response responnse
    // kullanarak postman deki citinin aynıüisni elde edecegiz. Response body kısmını yazdirir.
    @Test
    public void getMethod02(){

        Response response = given().
                when().
                get("https://restful-booker.herokuapp.com/booking");

        //response body console yazdirmak icin
        response.prettyPrint();

        //Status code console yazdirmak icin.
        System.out.println("Status Code: "+response.getStatusCode());

        //Postmandaki status line consolda gormek icin
        System.out.println("Status Line: " +response.getStatusLine());

        //Header kismindaki content type yazdirilir.
        // 1.) Yol:
        System.out.println("Content Type: "+response.getContentType());
        //2.) Yol:
        System.out.println("Content Type -2"+response.header("Content-Type"));

        //headerdaki tum bilgiler icin

        System.out.println(response.getHeaders());

        //headers ta spesific bilgi almak icin (date almak icin)
        System.out.println(response.header("Date"));

        response.
                then().
                assertThat().//hard asser hata buldugunda devam eder
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");
















    }
}