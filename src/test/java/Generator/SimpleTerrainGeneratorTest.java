package Generator;

import Generator.Options.TerrainConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleTerrainGeneratorTest {

    @Test
    void testOutputDimensions() {
        TerrainConfig config = new TerrainConfig();
        config.width = 10;
        config.height = 20;

        SimpleTerrainGenerator generator = new SimpleTerrainGenerator(config);
        TerrainData data = generator.generate();

        assertEquals(10, data.getWidth());
        assertEquals(20, data.getHeight());
    }
}
