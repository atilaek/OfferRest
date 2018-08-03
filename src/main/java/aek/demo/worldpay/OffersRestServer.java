package aek.demo.worldpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * OffersRestServer implements a Spring Boot application that
 * reads the Json file and provides create, read, update, delete operations to REST service via http.
 *
 * @author Atila Ekimci
 */
@SpringBootApplication
public class OffersRestServer {

    public static void main(String[] args) {
        SpringApplication.run(OffersRestServer.class, args);
    }
}
