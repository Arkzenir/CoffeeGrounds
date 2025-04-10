package Generator;

import Generator.Features.JsonFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FeatureFactory {
    public static JsonFeature loadFromFile(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filepath), JsonFeature.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load feature from " + filepath, e);
        }
    }
}
