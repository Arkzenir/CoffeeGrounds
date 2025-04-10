package Generator;

import Generator.Features.JsonFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FeatureFactory {
    public static JsonFeature loadFromFile(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream stream = FeatureFactory.class.getClassLoader().getResourceAsStream(filepath)) {
            if (stream == null) {
                throw new RuntimeException("Feature resource not found: " + filepath);
            }
            return mapper.readValue(stream, JsonFeature.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load feature from " + filepath, e);
        }
    }

    public static List<JsonFeature> loadAllFromDirectory(String dirPath) {
        List<JsonFeature> features = new ArrayList<>();
        try {
            var dir = Objects.requireNonNull(FeatureFactory.class.getClassLoader().getResource(dirPath));
            var uri = dir.toURI();
            var folder = new java.io.File(uri);
            var files = folder.listFiles((d, name) -> name.endsWith(".json"));

            if (files != null) {
                for (var file : files) {
                    try (InputStream stream = FeatureFactory.class.getClassLoader().getResourceAsStream(dirPath + "/" + file.getName())) {
                        if (stream != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            features.add(mapper.readValue(stream, JsonFeature.class));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to load features from directory: " + dirPath);
            e.printStackTrace();
        }
        return features;
    }
}
