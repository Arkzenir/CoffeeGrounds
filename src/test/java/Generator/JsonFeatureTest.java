package Generator;

import Generator.Features.JsonFeature;
import Generator.TerrainData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonFeatureTest {

    @Test
    void testJsonFeatureApplication() {
        JsonFeature feature = new JsonFeature();
        feature.name = "Boost";
        feature.radius = 1;
        feature.elevationBoost = 10;

        TerrainData data = new TerrainData(3, 3, 100);
        feature.applyFeature(data, 1, 1);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertTrue(data.getDepthAt(x, y) >= 0);
            }
        }
    }
}
