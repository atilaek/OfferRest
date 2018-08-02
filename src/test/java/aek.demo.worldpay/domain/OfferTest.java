package aek.demo.worldpay.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
    public void isCancelledWillReturnTrueForCancelledOffer() {
        assertTrue(createOffer("true", "08-25-2018").isCancelled());
    }

    @Test
    public void isCancelledWillReturnFalseForNOTCancelledOffer() {
        assertFalse(createOffer("false", "08-25-2018").isCancelled());
    }

    @Test
    public void isCancelledWillReturnTrueForCorruptedTexts() {
        assertTrue(createOffer("ture", "08-25-2018").isCancelled());
    }

    @Test
    public void isOutdatedWillReturnTrueForOutdatedOffer() {
        assertTrue(createOffer("true", "01-01-2005").isOutdated());
    }

    @Test
    public void isOutdatedWillReturnFalseForNOTOutdateOffer() {
        assertFalse(createOffer("true", "01-01-2100").isOutdated());
    }

    @Test
    public void toStringWillReturnExpectedString() {
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
