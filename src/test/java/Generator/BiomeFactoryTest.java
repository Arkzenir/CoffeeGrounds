package Generator;

import Generator.Biomes.BiomeFactory;
import Generator.Biomes.JsonBiome;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class BiomeFactoryTest {

    @Test
    void testLoadJsonBiomeFromFile() throws Exception {
        Path path = Path.of("test_biome.json");
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write("""
                {
                    "name": "Forest",
                    "color": "#228B22"
                }
            """);
        }

        JsonBiome biome = BiomeFactory.loadFromFile(path.toString());
        assertEquals("Forest", biome.name);
        assertEquals("#228B22", biome.color);

        assertTrue(path.toFile().delete());
    }
}
