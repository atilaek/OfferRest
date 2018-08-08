package aek.demo.worldpay.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing basic functions of Offer Pojo's new implemented functions
 *
 * @author Atila Ekimci
 */
public class OfferTest {

    @Before
    public void setup() throws Exception {
        new Price(20, "Pound");
    }

    @Test
    public void isCancelled_ShouldReturnTrueForCancelledOffer() {
        assertTrue(createOffer("true", "08-25-2018").isCancelled());
    }

    @Test
    public void isCancelled_ShouldReturnFalseForNotCancelledOffer() {
        assertFalse(createOffer("false", "08-25-2018").isCancelled());
    }

    @Test
    public void isCancelled_ShouldReturnTrueForCorruptedTexts() {
        assertTrue(createOffer("ture", "08-25-2018").isCancelled());
    }

    @Test
    public void isOutdated_ShouldReturnTrueForOutdatedOffer() {
        assertTrue(createOffer("true", "01-01-2005").isOutdated());
    }

    @Test
    public void isOutdated_ShouldReturnFalseForNotOutdatedOffer() {
        assertFalse(createOffer("true", "01-01-2100").isOutdated());
    }

    @Test
    public void toString_ShouldReturnExpectedString() {
        final String expectedString = "Id: \'1'\n" +
                "Name: \'testOffer\'\n" +
                "Price: amount: \'20\' currency: \'Pound\'\n" +
                "Description: \'test description\'\n" +
                "expirationDate: \'01-01-2020\'\n" +
                "Expired: \'true\'\n" +
                "\n";
        assertEquals("ToString function returned different result",
                expectedString, createOffer("true", "01-01-2020").toString());
    }

    private Offer createOffer(String cancelled, String expirationDate) {
        return new Offer(1,
                "testOffer",
                new Price(20, "Pound"),
                "test description",
                expirationDate,
                cancelled
        );
    }

}
