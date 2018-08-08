package aek.demo.worldpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Price POJO based on http://www.jsonschema2pojo.org
 *
 * @author Atila Ekimci
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "currency"
})
public class Price {

    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("currency")
    private String currency;

    /**
     * No args constructor for use in serialization.
     */
    public Price() {
    }

    /**
     * Price Constructor used for testing.
     *
     * @param amount   the amount of the price.
     * @param currency the current type of the price.
     */
    public Price(Integer amount, String currency) {
        super();
        this.amount = amount;
        this.currency = currency;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "amount: '" + this.amount + "' currency: '" + this.currency + "'";
    }

}
