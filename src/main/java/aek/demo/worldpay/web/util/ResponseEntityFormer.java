package aek.demo.worldpay.web.util;

import org.springframework.http.ResponseEntity;

/**
 * Exception throwing class for giving controlled exceptions in Controller Layer for REST.
 */
public final class ResponseEntityFormer {

    private ResponseEntityFormer() {
        throw new AssertionError();
    }

    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param resource return Ok ResponseEntity if found, otherwise return notFound ResponseEntity
     */
    public static ResponseEntity formResponse(final Object resource) {
        if (resource == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resource);
    }

}
