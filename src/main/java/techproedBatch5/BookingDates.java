package techproedBatch5;

import org.codehaus.jackson.annotate.JsonProperty;

public class BookingDates {
    @JsonProperty("checkin")
    private String checkin;
    @JsonProperty("checkout")
    private String checkout;

    @JsonProperty("checkin")
    public String getCheckin() {
        return checkin;
    }

    @JsonProperty("checkin")
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    @JsonProperty("checkout")
    public String getCheckout() {
        return checkout;
    }

    @JsonProperty("checkout")
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public BookingDates() {
    }

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "BookingDates{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }

    /*Pojoda olmasi gereken ler
1) Json dan key olanlar icin variable olusturun ve bu veablelarin access modifier lari private olmali
2) /Her variable icin mutlak getter ve setter olmali
3) Parametresin construction olmali
4) olusturdugumuz variable icin parametreli constructor lari olusturalim.
5)toString Methodu olusturacagiz
*/

}
