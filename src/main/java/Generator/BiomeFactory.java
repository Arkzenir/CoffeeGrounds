package Generator;

import Generator.Biomes.JsonBiome;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class BiomeFactory {
    public static JsonBiome loadFromFile(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filepath), JsonBiome.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load biome from " + filepath, e);
        }
    }
}
