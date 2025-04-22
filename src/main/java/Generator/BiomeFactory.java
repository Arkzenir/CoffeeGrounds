package Generator;

import Generator.Biomes.JsonBiome;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BiomeFactory {
    public static JsonBiome loadFromFile(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream stream = BiomeFactory.class.getClassLoader().getResourceAsStream(filepath)) {
            if (stream == null) {
                throw new RuntimeException("Biome resource not found: " + filepath);
            }
            return mapper.readValue(stream, JsonBiome.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load biome from " + filepath, e);
        }
    }

    //Possibly deprecated
    public static List<JsonBiome> loadAllFromDirectory(String dirPath) {
        List<JsonBiome> biomes = new ArrayList<>();
        try {
            var dir = Objects.requireNonNull(BiomeFactory.class.getClassLoader().getResource(dirPath));
            var uri = dir.toURI();
            var folder = new java.io.File(uri);
            var files = folder.listFiles((d, name) -> name.endsWith(".json"));

            if (files != null) {
                for (var file : files) {
                    try (InputStream stream = BiomeFactory.class.getClassLoader().getResourceAsStream(dirPath + "/" + file.getName())) {
                        if (stream != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            biomes.add(mapper.readValue(stream, JsonBiome.class));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to load biomes from directory: " + dirPath);
            e.printStackTrace();
        }
        return biomes;
    }
}
