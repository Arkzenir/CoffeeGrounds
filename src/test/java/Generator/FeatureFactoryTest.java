package Generator;

import Generator.Features.FeatureFactory;
import Generator.Features.JsonFeature;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class FeatureFactoryTest {

    @Test
    void testLoadJsonFeatureFromFile() throws Exception {
        Path path = Path.of("test_feature.json");
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write("""
                {
                    "name": "Crater",
                    "radius": 2,
                    "elevationBoost": -5
                }
            """);
        }

        JsonFeature feature = FeatureFactory.loadFromFile(path.toString());
        assertEquals("Crater", feature.name);
        assertEquals(2, feature.radius);
        assertEquals(-5f, feature.elevationBoost);

        assertTrue(path.toFile().delete());
    }
}
