package Utilities;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
public class JsonUtil {

    //bu class icerisine serialization ve deserialization methodşlari olusturacagiz ve ihtiyac oldukca kullanacagiz.

    private static ObjectMapper mapper; // diger classlardan ulasabilmek icin methodlarin disina class icinde olusturduk


    //methodlardan onde olusmasi icin static block icerisinde mapper objesi olusturduk.
    static {
        mapper = new ObjectMapper();// mapper objemizi hemen olusturmus olduk.
    }

    // Java objesini JSon formatina ceviren method.
    public static String convertJavaToJson(Object object) { //veri tipi olarak ne yazarsak onu json formatina cevirir.
        String jsonResult = "";

        try {
            jsonResult = mapper.writeValueAsString(object);  //Java objesi Json formatina cevirildi. Olusabilecek hatalar icin
            //try catch islemi yapildi.
        } catch (JsonGenerationException e) {
            System.out.println("Java objesini cevirirken hat olustu." + e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("Java objesini cevirirken hat olustu." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Java objesini cevirirken hat olustu." + e.getMessage());
        }

        return jsonResult;
    }

    // Json formatini Java formatina cevirme  ==> DeSerialization
    //public static Object convertJsonToJava(){

    public static  <T> T convertJsonToJava(String json, Class<T> cls) {
        //Generik bir medhod urettik. Bu method ile Json formatini cevirmek istedigimiz formata gore cevirecegiz.
        //return type method kullanilirken belirtiliyor.

        T javaResult = null; // objelere bos degerr atamak icin null kullanilir.
        try {
            javaResult = mapper.readValue(json, cls);
            //readValue() methodu ObjectMapper class indan geliyor veJson formatini Java formatina ceviriyor.

        } catch (JsonParseException e) {
            System.out.println("Json  objesini Java formatina  cevirirken hata olustu." + e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("Json  objesini Java formatina  cevirirken hata olustu." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Json  objesini Java formatina  cevirirken hata olustu." + e.getMessage());
        }
        return javaResult;
        //Testerlar bu methodu cok kullanir. asil kullanilacak olan methoddur.
        // Bu islem De Serialization islemidir.

        }
}