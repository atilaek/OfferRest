package aek.demo.worldpay.web.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import aek.demo.worldpay.domain.Offer;
import aek.demo.worldpay.domain.Price;
import aek.demo.worldpay.service.IOfferService;

/**
 * Testing OfferController class functions with mocking OfferService including exceptions
 *
 * @author Atila Ekimci
 */
@RunWith(MockitoJUnitRunner.class)
public class OfferControllerTest {

    @Mock
    private IOfferService offerServiceMock;

    @InjectMocks
    private OfferController offerControllerTest;

    @Before
    public void setup() throws Exception {
        Map<Integer, Offer> offers = new HashMap<>();
        offers.put(1, createOffer(1, "test1"));
        offers.put(2, createOffer(2, "test2"));
        String allOffers = (new ArrayList<>(offers.values()).toString());
        when(offerServiceMock.getAllOffers()).thenReturn(allOffers);
        when(offerServiceMock.getOfferById(anyInt())).thenReturn(offers.get(1));
        when(offerServiceMock.addOffer(any(Offer.class))).thenReturn(3);
    }

    @Test
    public void getAllOffersWillReturnAllTheOffers() {
        Assert.assertTrue(offerControllerTest.getAllOffers().getBody().contains("test1"));
    }

    @Test
    public void getOfferByIDWillReturnTheExpectedOffer() {
        Assert.assertTrue(((Offer)offerControllerTest.getOfferById(1).getBody()).getName().equals("test1"));
    }

    @Test
    public void getOfferByNONExistingIDWillReturnNotFoundResponse() {
        when(offerServiceMock.getOfferById(anyInt())).thenReturn(null);
        Assert.assertTrue(offerControllerTest.getOfferById(40).getStatusCode()
                                  .equals(ResponseEntity.notFound().build().getStatusCode()));
    }

    @Test
    public void deleteOfferWillReturnTheExpectedId() {
        Assert.assertTrue(offerControllerTest.deleteOffer(1).getBody().equals(1));
    }

    @Test
    public void deleteOfferByNONExistingIDWillReturnNotFoundResponse() {
        when(offerServiceMock.getOfferById(anyInt())).thenReturn(null);
        Assert.assertTrue(offerControllerTest.deleteOffer(40).getStatusCode()
                                  .equals(ResponseEntity.notFound().build().getStatusCode()));
    }

    @Test
    public void updateOfferWillReturnTheExpectedId() {
        Assert.assertTrue(offerControllerTest.updateOffer(1, new Offer()).getBody().equals(1));
    }

    @Test
    public void updateOfferByNONExistingIDWillReturnNotFoundResponse() {
        when(offerServiceMock.getOfferById(anyInt())).thenReturn(null);
        Assert.assertTrue(offerControllerTest.updateOffer(40, new Offer()).getStatusCode()
                                  .equals(ResponseEntity.notFound().build().getStatusCode()));
    }

    @Test
    public void addOfferWillReturnTheExpectedId() {
        Assert.assertTrue(offerControllerTest.addOffer(new Offer()).getBody().equals(3));

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
