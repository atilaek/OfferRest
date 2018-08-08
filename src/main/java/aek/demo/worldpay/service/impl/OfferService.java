package aek.demo.worldpay.service.impl;

import aek.demo.worldpay.domain.Offer;
import aek.demo.worldpay.service.IOfferService;
import aek.demo.worldpay.service.exception.OfferExistsException;
import aek.demo.worldpay.service.exception.OfferNotFoundException;
import aek.demo.worldpay.service.repository.impl.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OfferService service of for manipulating .json Offers data
 * Calls Offer repository and being called by OfferController via OfferService Interface
 *
 * @author Atila Ekimci
 */
@Service
public class OfferService implements IOfferService {

    @Autowired
    private OfferRepository offerRepository;

    public String getAllOffers() {
        return offerRepository.getAllOffers().toString();
    }

    /**
     * Gets the offer based on id.
     *
     * @param id Id of the offer @{@link Integer}
     * @return @{@link Offer}.
     */
    public Offer getOfferById(final int id) {
        if (offerRepository.containsKey(id)) {
            final Offer offer = offerRepository.getOffer(id);
            if (!offer.isCancelled()) {
                if (!offer.isOutdated()) {
                    return offerRepository.getOffer(id);
                } else {
                    offer.setCancelled("true");
                }
            }
        }
        return null;
    }

    /**
     * Deletes the offer data.
     *
     * @param id Id of the offer @{@link Integer}
     */
    public void deleteOffer(final int id) {
        if (!offerRepository.containsKey(id)) {
            throw new OfferNotFoundException("Unable to find offer with id " + id);
        }
        offerRepository.deleteOffer(id);
        offerRepository.saveRepository();
    }

    /**
     * Updates an existing offer.
     *
     * @param id            Id of the offer @{@link Integer}
     * @param updatingOffer The new offer data to be replaced with @{@link Offer}
     */
    public void updateOffer(final Offer updatingOffer, final int id) {
        if (!offerRepository.containsKey(id)) {
            throw new OfferNotFoundException("Unable to find offer with id " + id);
        }
        updatingOffer.setId(id);
        offerRepository.updateOffer(id, updatingOffer);
        offerRepository.saveRepository();
    }

    /**
     * Adds a new offer.
     *
     * @param newOffer The new offer data to add @{@link Offer}
     * @return @{@link int} that has the id of the added offer
     */
    public int addOffer(final Offer newOffer) {
        if (offerRepository.hasOffer(newOffer)) {
            throw new OfferExistsException("Offer " + newOffer + " already exists.");
        }
        final int key = offerRepository.addOffer(newOffer);
        offerRepository.saveRepository();
        return key;
    }

}
