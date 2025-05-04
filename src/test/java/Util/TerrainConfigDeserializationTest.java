package Util;

import Generator.Options.TerrainConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TerrainConfigDeserializationTest {

    @Test
    void testDeserializeTerrainConfig() throws Exception {
        String json = """
            {
                "width": 16,
                "height": 16,
                "depth": 64,
                "seed": 123,
                "biomeDirectory": "biomes",
                "featureDirectory": "features"
            }
        """;

        ObjectMapper mapper = new ObjectMapper();
        TerrainConfig config = mapper.readValue(json, TerrainConfig.class);

        assertEquals(16, config.width);
        assertEquals("biomes", config.biomeDirectory);
    }
}
