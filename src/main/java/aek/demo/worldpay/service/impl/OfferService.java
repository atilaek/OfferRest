package aek.demo.worldpay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aek.demo.worldpay.domain.Offer;
import aek.demo.worldpay.service.IOfferService;
import aek.demo.worldpay.service.exception.OfferExistsException;
import aek.demo.worldpay.service.exception.OfferNotFoundException;
import aek.demo.worldpay.service.repository.impl.OfferRepository;

/**
 * OfferService service of for manipulating .json Offers data
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

    public void deleteOffer(final int id) {
        if (!offerRepository.containsKey(id)) {
            throw new OfferNotFoundException("Unable to find offer with id " + id);
        }
        offerRepository.deleteOffer(id);
        offerRepository.saveRepository();
    }

    public void updateOffer(final Offer updatingOffer, final int id) {
        if (!offerRepository.containsKey(id)) {
            throw new OfferNotFoundException("Unable to find offer with id " + id);
        }
        updatingOffer.setId(id);
        offerRepository.updateOffer(id, updatingOffer);
        offerRepository.saveRepository();
    }

    public int addOffer(final Offer newOffer) {
        if (offerRepository.hasOffer(newOffer)) {
            throw new OfferExistsException("Offer " + newOffer + " already exists.");
        }
        final int key = offerRepository.addOffer(newOffer);
        offerRepository.saveRepository();
        return key;
    }

}
