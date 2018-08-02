package aek.demo.worldpay.service.exception;

/**
 * Exception throwing class for giving controlled exceptions in Service Layer for Spring Boot App.
 *
 * @author Atila Ekimci
 */
final public class OfferNotFoundException extends RuntimeException {

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
