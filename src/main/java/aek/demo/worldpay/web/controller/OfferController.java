package aek.demo.worldpay.web.controller;

import aek.demo.worldpay.domain.Offer;
import aek.demo.worldpay.service.IOfferService;
import aek.demo.worldpay.web.util.ResponseEntityFormer;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * OfferController for REST service that calls OfferService for operations.
 *
 * @author Atila Ekimci
 */
@Controller
@RequestMapping("/Worldpay")
public class OfferController {

    @Autowired
    private IOfferService offerService;

    @GetMapping(path = "/offers")
    public ResponseEntity<String> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    /**
     * Gets the offer based on id.
     *
     * @param id Id of the offer @{@link Integer}
     * @return @{@link ResponseEntity} that has the offer.
     */
    @GetMapping(path = "/offer/{id}")
    public ResponseEntity getOfferById(@PathVariable("id") final Integer id) {
        Preconditions.checkNotNull(id, "id can not be null");
        return ResponseEntityFormer.formResponse(offerService.getOfferById(id));
    }

    /**
     * Deletes the offer data.
     *
     * @param id Id of the offer @{@link Integer}
     * @return @{@link ResponseEntity} that has the id of the deleted offer
     */
    @DeleteMapping(path = "/offer/{id}")
    public ResponseEntity deleteOffer(@PathVariable("id") final Integer id) {
        Preconditions.checkNotNull(id, "id can not be null");
        if (offerService.getOfferById(id) == null) {
            return ResponseEntityFormer.formResponse(offerService.getOfferById(id));
        }

        offerService.deleteOffer(id);
        return ResponseEntity.ok(id);
    }

    /**
     * Updates an existing offer.
     *
     * @param id    Id of the offer @{@link Integer}
     * @param offer The new offer data to be replaced with @{@link Offer}
     * @return @{@link ResponseEntity} that has the id of the updated offer
     */
    @PutMapping(path = "/offer/{id}")
    public ResponseEntity updateOffer(@PathVariable("id") final Integer id, @RequestBody final Offer offer) {
        Preconditions.checkNotNull(id, "id can not be null");
        Preconditions.checkNotNull(offer, "offer can not be null");
        if (offerService.getOfferById(id) == null) {
            return ResponseEntityFormer.formResponse(offerService.getOfferById(id));
        }

        offerService.updateOffer(offer, id);
        return ResponseEntity.ok(id);
    }

    /**
     * Adds a new offer.
     *
     * @param offer The new offer data to add @{@link Offer}
     * @return @{@link ResponseEntity} that has the id of the added offer
     */
    @PostMapping
    public ResponseEntity addOffer(@RequestBody final Offer offer) {
        Preconditions.checkNotNull(offer, "offer can not be null");

        final int key = offerService.addOffer(offer);
        return ResponseEntity.ok(key);
    }
}
