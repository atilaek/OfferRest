package aek.demo.worldpay.service.repository;

import java.util.Map;

import aek.demo.worldpay.domain.Offer;

/**
 * Interface for OfferRepository.
 *
 * @author Atila Ekimci
 */
public interface IOfferRepository {

    boolean containsKey(final int id);

    Offer getOffer(final int id);

    Map<Integer, Offer> getAllOffers();

    boolean hasOffer(final Offer offer);

    int addOffer(final Offer newOffer);

    int updateOffer(final int id, final Offer newOffer);

    int deleteOffer(final int id);

    void saveRepository();

}
