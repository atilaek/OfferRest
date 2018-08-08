package aek.demo.worldpay.service.impl;

import aek.demo.worldpay.domain.Offer;
import aek.demo.worldpay.domain.Price;
import aek.demo.worldpay.service.exception.OfferExistsException;
import aek.demo.worldpay.service.exception.OfferNotFoundException;
import aek.demo.worldpay.service.repository.impl.OfferRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Testing OfferService class functions with mocking OfferRepository including exceptions
 *
 * @author Atila Ekimci
 */

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

    @Mock
    private OfferRepository offerRepositoryMock;

    @InjectMocks
    private OfferService offerServiceTest;

    @Before
    public void setup() throws Exception {
        Map<Integer, Offer> offers = new HashMap<>();
        offers.put(1, createOffer(1, "test1"));
        offers.put(2, createOffer(2, "test2"));
        when(offerRepositoryMock.getAllOffers()).thenReturn(offers);
        when(offerRepositoryMock.getOffer(anyInt())).thenReturn(offers.get(1));
        when(offerRepositoryMock.containsKey(anyInt())).thenReturn(true);
        when(offerRepositoryMock.addOffer(any(Offer.class))).thenReturn(3);
        when(offerRepositoryMock.deleteOffer(anyInt())).thenReturn(1);
    }

    @Test
    public void getAllOffers_ShouldReturnAllTheOffers() {
        Assert.assertTrue(offerServiceTest.getAllOffers().contains("test1"));
    }

    @Test
    public void getOfferByID_ShouldReturnTheExpectedOffer() {
        Assert.assertTrue(offerServiceTest.getOfferById(1).getName().equals("test1"));
    }

    @Test
    public void getOfferByID_ShouldSetCancelledToTrueForTheOutdatedOffer() {
        final Offer offer = new Offer(1,
                "WillBeCancelled",
                new Price(20, "Pound"),
                "This Offer is not cancelled but outdated",
                "01-01-1995",
                "false"
        );

        when(offerRepositoryMock.getOffer(anyInt())).thenReturn(offer);
        Assert.assertNull(offerServiceTest.getOfferById(1));
    }

    @Test
    public void deleteOffer_ShouldDeleteTheOffer() {
        offerServiceTest.deleteOffer(1);
    }

    @Test(expected = OfferNotFoundException.class)
    public void deleteOffer_ShouldThrowException_IfOfferDoesntExist() {
        when(offerRepositoryMock.containsKey(anyInt())).thenReturn(false);
        offerServiceTest.deleteOffer(5);
    }

    @Test
    public void updateOffer_ShouldUpdateTheOffer() {
        offerServiceTest.updateOffer(new Offer(), 1);
    }

    @Test(expected = OfferNotFoundException.class)
    public void updateOffer_ShouldThrowException_IfOfferDoesntExist() {
        when(offerRepositoryMock.containsKey(anyInt())).thenReturn(false);
        offerServiceTest.updateOffer(new Offer(), 5);
    }

    @Test
    public void addOffer_ShouldReturnTheNewOfferId() {
        Assert.assertTrue(offerServiceTest.addOffer(new Offer()) == 3);

    }

    @Test(expected = OfferExistsException.class)
    public void addExistingOffer_ShouldThrowException_IfOfferAlreadyExists() {
        when(offerRepositoryMock.hasOffer(any(Offer.class))).thenReturn(true);
        offerServiceTest.addOffer(createOffer(2, "Existing Offer"));
    }

    private Offer createOffer(Integer id, String name) {
        return new Offer(id,
                name,
                new Price(20, "Pound"),
                "test description",
                "01-01-2050",
                "false"
        );
    }

}
