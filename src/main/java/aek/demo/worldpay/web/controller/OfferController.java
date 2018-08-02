package aek.demo.worldpay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Preconditions;

import aek.demo.worldpay.domain.Offer;
import aek.demo.worldpay.service.IOfferService;
import aek.demo.worldpay.web.util.ResponseEntityFormer;

/**
 * OfferController for REST.
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

    @GetMapping(path = "/offer/{id}")
    public ResponseEntity getOfferById(@PathVariable("id") final Integer id) {
        Preconditions.checkNotNull(id, "id can not be null");
        return ResponseEntityFormer.formResponse(offerService.getOfferById(id));
    }

    @DeleteMapping(path = "/offer/{id}")
    public ResponseEntity deleteOffer(@PathVariable("id") final Integer id) {
        Preconditions.checkNotNull(id, "id can not be null");
        if (offerService.getOfferById(id) == null) {
            return ResponseEntityFormer.formResponse(offerService.getOfferById(id));
        }

        offerService.deleteOffer(id);
        return ResponseEntity.ok(id);
    }

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

    @PostMapping
    public ResponseEntity addOffer(@RequestBody final Offer offer) {
        Preconditions.checkNotNull(offer, "offer can not be null");

        final int key = offerService.addOffer(offer);
        return ResponseEntity.ok(key);
    }
}
