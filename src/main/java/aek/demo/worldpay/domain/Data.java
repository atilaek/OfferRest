package aek.demo.worldpay.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Data POJO based on http://www.jsonschema2pojo.org
 *
 * @author Atila Ekimci
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "Offer"
})
public class Data {

    @JsonProperty("Offer")
    private List<Offer> offer = null;

    @JsonProperty("Offer")
    public List<Offer> getOffer() {
        return offer;
    }

    @JsonProperty("Offer")
    public void setOffer(List<Offer> offer) {
        this.offer = offer;
    }

}
