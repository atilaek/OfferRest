package aek.demo.worldpay.service.exception;

/**
 * Exception throwing class for giving controlled exceptions
 * if Offer is not found
 * in Service Layer for Spring Boot App.
 *
 * @author Atila Ekimci
 */
public final class OfferNotFoundException extends RuntimeException {

    public OfferNotFoundException() {
        super();
    }

    public OfferNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OfferNotFoundException(final String message) {
        super(message);
    }

    public OfferNotFoundException(final Throwable cause) {
        super(cause);
    }

}
