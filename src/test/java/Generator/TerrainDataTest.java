package Generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TerrainDataTest {

    @Test
    void testInitialization() {
        TerrainData data = new TerrainData(5, 6, 128);
        assertNotNull(data.heightMap);
        assertEquals(5, data.getWidth());
        assertEquals(6, data.getHeight());
        assertEquals(128, data.getMaxDepth());
    }

    @Test
    void testHeightClamping() {
        TerrainData data = new TerrainData(3, 3, 10);
        data.setDepthAt(0, 0, 100);
        assertEquals(10, data.getDepthAt(0, 0));
        data.setDepthAt(1, 1, -100);
        assertEquals(-10, data.getDepthAt(1, 1));
    }
}
