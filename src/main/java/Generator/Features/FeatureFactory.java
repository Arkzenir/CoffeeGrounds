package Generator.Features;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FeatureFactory {
    public static JsonFeature loadFromFile(String path) {
        ObjectMapper mapper = new ObjectMapper();

        Path externalPath = Path.of(path);
        if (Files.exists(externalPath)) {
            try (InputStream in = Files.newInputStream(externalPath)) {
                return mapper.readValue(in, JsonFeature.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load feature from external path: " + path, e);
            }
        }

        InputStream resourceStream = FeatureFactory.class.getClassLoader().getResourceAsStream(path);
        if (resourceStream != null) {
            try {
                return mapper.readValue(resourceStream, JsonFeature.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load feature from internal resource: " + path, e);
            }
        }

        throw new RuntimeException("Feature file not found at path: " + path);
    }
}
