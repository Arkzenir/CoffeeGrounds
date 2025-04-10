package Generator;

import Generator.Biomes.JsonBiome;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BiomeFactory {
    public static JsonBiome loadFromFile(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filepath), JsonBiome.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load biome from " + filepath, e);
        }
    }

    public static List<JsonBiome> loadAllFromDirectory(String dirPath) {
        List<JsonBiome> biomes = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                try {
                    biomes.add(mapper.readValue(file, JsonBiome.class));
                } catch (IOException e) {
                    System.err.println("Failed to load biome: " + file.getName());
                }
            }
        }
        return biomes;
    }
}
