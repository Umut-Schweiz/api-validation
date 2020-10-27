package techproedBatch5;
import Utilities.JsonUtil;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
public class ObjectMapperWithPojo extends TestBase {
    //Java objesini Json formatina Pojo ile cevirecegiz.
    @Test
    public void javaToJson(){
        BookingDates bookingDates =new BookingDates("2020-10-20","2020-10-25");
        System.out.println(bookingDates); // BookingDates{checkin='2020-10-20', checkout='2020-10-25'}
        String JsonFromPojo = JsonUtil.convertJavaToJson(bookingDates);
        System.out.println(JsonFromPojo); //{"checkin":"2020-10-20","checkout":"2020-10-25"}
    }
    @Test
    public void jsonToJava(){
        Response response = given().
                spec(spec01).
                when().
                get("booking/3");
        response.prettyPrint();
        //Json data yi pojo classi ile java formatina cevirecegiz.
        Booking jsonToPojoApi= JsonUtil.convertJsonToJava(response.asString(),Booking.class);
        System.out.println(jsonToPojoApi);
        BookingDates bookingDates =new BookingDates("2015-07-10","2016-05-21");
        Booking booking = new Booking("Mary","Smith",973,true,bookingDates,"");
        response.
                then().
                assertThat().
                statusCode(200);
        assertEquals(booking.getBookingdates().getCheckin(),jsonToPojoApi.getBookingdates().getCheckin());
        assertEquals(booking.getBookingdates().getCheckout(),jsonToPojoApi.getBookingdates().getCheckout());
    }
}
