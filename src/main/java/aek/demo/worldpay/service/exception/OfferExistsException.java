package aek.demo.worldpay.service.exception;

/**
 * Exception throwing class for giving controlled exceptions
 * if Offer already exists
 * in Service Layer for Spring Boot App.
 *
 * @author Atila Ekimci
 */
public final class OfferExistsException extends RuntimeException {

    public OfferExistsException() {
        super();
    }

    public OfferExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OfferExistsException(final String message) {
        super(message);
    }

    public OfferExistsException(final Throwable cause) {
        super(cause);
    }

}
