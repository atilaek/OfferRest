package aek.demo.worldpay.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Testing basic functions of Price Pojo's new implemented functions
 *
 * @author Atila Ekimci
 */

@RunWith(MockitoJUnitRunner.class)
public class PriceTest {

	private Price price;

	@Before
	public void setup() throws Exception {
	    price = new Price(20, "Pound");
	}

    @Test
    public void toStringReturnsExpectedString() {
        final String expectedString = "amount: \'20\' currency: \'Pound\'";
	    assertEquals("ToString function returned different result",
                      expectedString, price.toString());

    }

}
