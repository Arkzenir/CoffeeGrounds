package Generator;

import Generator.Features.JsonFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeatureFactory {
    public static JsonFeature loadFromFile(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filepath), JsonFeature.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load feature from " + filepath, e);
        }
    }

    public static List<JsonFeature> loadAllFromDirectory(String dirPath) {
        List<JsonFeature> features = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                try {
                    features.add(mapper.readValue(file, JsonFeature.class));
                } catch (IOException e) {
                    System.err.println("Failed to load feature: " + file.getName());
                }
            }
        }
        return features;
    }
}
