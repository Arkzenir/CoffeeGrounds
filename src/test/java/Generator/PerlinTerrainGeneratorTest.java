package Generator;

import Generator.Options.TerrainConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PerlinTerrainGeneratorTest {

    @Test
    void testOutputDimensions() {
        TerrainConfig config = new TerrainConfig();
        config.width = 32;
        config.height = 32;
        config.depth = 64;
        config.seed = 123L;

        PerlinTerrainGenerator generator = new PerlinTerrainGenerator(config);
        TerrainData data = generator.generate();

        assertEquals(32, data.getWidth());
        assertEquals(32, data.getHeight());
    }

    @Test
    void testDepthRange() {
        TerrainConfig config = new TerrainConfig();
        config.width = 10;
        config.height = 10;
        config.depth = 20;
        config.seed = 5L;

        PerlinTerrainGenerator generator = new PerlinTerrainGenerator(config);
        TerrainData data = generator.generate();

        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                int depth = data.getDepthAt(x, y);
                assertTrue(depth >= -config.depth && depth <= config.depth);
            }
        }
    }
}
