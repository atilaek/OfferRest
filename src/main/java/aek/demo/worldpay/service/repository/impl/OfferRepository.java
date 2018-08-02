package aek.demo.worldpay.service.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import aek.demo.worldpay.domain.Data;
import aek.demo.worldpay.domain.Offer;
import aek.demo.worldpay.service.repository.IOfferRepository;
import aek.demo.worldpay.service.repository.JsonReadWriter;

/**
 * Repository of Offers that mainly reads from Json and has core functions.
 *
 * @author Atila Ekimci
 */
@Repository
public class OfferRepository implements IOfferRepository {

    private static Data data = null;
    private Map<Integer, Offer> offers = new HashMap<>();

    public OfferRepository() {
        data = JsonReadWriter.readJsonFile();
        Offer offer;
        for (int i = 0; i < data.getOffer().size(); i++) {
            offer = data.getOffer().get(i);
            offers.put(offer.getId(), offer);
        }
    }

    public boolean containsKey(final int id) {
        return offers.containsKey(id);
    }

    public Offer getOffer(final int id) {
        return offers.get(id);
    }

    public Map<Integer, Offer> getAllOffers() {
        return offers;
    }

    public boolean hasOffer(final Offer offer) {
        return offers.containsValue(offer);
    }

    public int addOffer(final Offer newOffer) {
        int key = offers.size() + 1;
        newOffer.setId(key);
        offers.put(key, newOffer);
        return key;
    }

    public int updateOffer(final int id, final Offer newOffer) {
        offers.put(id, newOffer);
        return id;
    }

    public int deleteOffer(final int id) {
        offers.remove(id);
        return id;
    }

    public void saveRepository() {
        data.setOffer(new ArrayList<>(offers.values()));
        JsonReadWriter.writeToJsonFile(data);
    }

}
