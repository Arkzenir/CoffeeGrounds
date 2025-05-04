package Generator;

import Generator.Biomes.JsonBiome;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonBiomeTest {

    @Test
    void testJsonBiomeFields() {
        JsonBiome biome = new JsonBiome();
        biome.name = "Desert";
        biome.color = "#FFFF00";
        assertEquals("Desert", biome.name);
        assertEquals("#FFFF00", biome.color);
    }
}
