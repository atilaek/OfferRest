package aek.demo.worldpay.service.repository;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import aek.demo.worldpay.domain.Data;

/**
 * Class for json file reading.
 *
 * @author Atila Ekimci
 */
public class JsonReadWriter {

    private static final String fileName = "./offers_data.json";

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
