package Generator.Biomes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BiomeFactory {
    public static JsonBiome loadFromFile(String path) {
        ObjectMapper mapper = new ObjectMapper();

        // Try external file path
        Path externalPath = Path.of(path);
        if (Files.exists(externalPath)) {
            try (InputStream in = Files.newInputStream(externalPath)) {
                return mapper.readValue(in, JsonBiome.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load biome from external path: " + path, e);
            }
        }

        // Try internal resource
        InputStream resourceStream = BiomeFactory.class.getClassLoader().getResourceAsStream(path);
        if (resourceStream != null) {
            try {
                return mapper.readValue(resourceStream, JsonBiome.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load biome from internal resource: " + path, e);
            }
        }

        throw new RuntimeException("Biome file not found at path: " + path);
    }
}
