package aek.demo.worldpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Offer POJO based on http://www.jsonschema2pojo.org.
 *
 * @author Atila Ekimci
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "name",
        "Price",
        "description",
        "expirationDate",
        "cancelled"
})
public class Offer {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("Price")
    private Price price;
    @JsonProperty("description")
    private String description;
    @JsonProperty("expirationDate")
    private String expirationDate;
    @JsonProperty("cancelled")
    private String cancelled;

    /**
     * Offer Constructor used for testing.
     */
    public Offer() {
    }

    /**
     * Offer Constructor used for testing.
     *
     * @param name           name of the Offer
     * @param description    the description about the specific offer
     * @param price          the price of the offer
     * @param expirationDate the date until when the offer will be valid
     * @param cancelled      boolean data that tells if offer is cannceled (inactive)
     */
    public Offer(Integer id, String name, Price price, String description, String expirationDate, String cancelled) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.expirationDate = expirationDate;
        this.cancelled = cancelled;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(final int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("Price")
    public void setPrice(Price price) {
        this.price = price;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("expirationDate")
    public String getExpirationDate() {
        return expirationDate;
    }

    @JsonProperty("expirationDate")
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @JsonProperty("cancelled")
    public String getCancelled() {
        return cancelled;
    }

    @JsonProperty("cancelled")
    public void setCancelled(String cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Checks if the offer is not valid anymore.
     *
     * @return @{@link Boolean}
     */
    public Boolean isCancelled() {
        if (this.cancelled.equalsIgnoreCase("false")) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the offers expiration date is passed.
     *
     * @return @{@link Boolean}
     */
    public Boolean isOutdated() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date tempDate;
        try {
            tempDate = simpleDateFormat.parse(this.expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();

            //this will return true (yesOutdated) all the time
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, -1);
            tempDate = cal.getTime();
        }

        return tempDate.before(new Date());
    }

    @Override
    public String toString() {
        return "Id: '" + this.id + "'\n"
                + "Name: '" + this.name + "'\n"
                + "Price: " + this.price.toString() + "\n"
                + "Description: '" + this.description + "'\n"
                + "expirationDate: '" + this.expirationDate + "'\n"
                + "Expired: '" + this.cancelled + "'\n\n";
    }
}
