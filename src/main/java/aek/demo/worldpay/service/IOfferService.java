package aek.demo.worldpay.service;

import aek.demo.worldpay.domain.Offer;

/**
 * Interface for OfferService service.
 *
 * @author Atila Ekimci
 */
public interface IOfferService {

    String getAllOffers();

    Offer getOfferById(final int id);

    void deleteOffer(final int id);

    void updateOffer(final Offer updatingOffer, final int id);

    int addOffer(final Offer newOffer);
}
