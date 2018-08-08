package aek.demo.worldpay.service.repository;

import aek.demo.worldpay.domain.Data;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Class for json file reading.
 *
 * @author Atila Ekimci
 */
public class JsonReadWriter {

    private static final String fileName = "./offers_data.json";

    /**
     * Reads Json file through the static file path and put them in a @{@link Data} object.
     *
     * @return @{@link Data}
     */
    public static Data readJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        Data data = null;
        try {
            File file = new File(fileName);
            // Convert JSON string from file to Object
            data = mapper.readValue(file, Data.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Gets @{@link Data} object and writes to Json file through the static file path.
     *
     * @param data Data object that has the list of offers
     */
    public static void writeToJsonFile(Data data) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Convert JSON string from file to Object
            mapper.writeValue(new File(fileName), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
