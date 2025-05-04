package Util;

import Utils.CompositeTerrainExporter;
import Generator.TerrainData;
import Generator.Biomes.JsonBiome;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompositeTerrainExporterTest {

    @Test
    void testCompositeTerrainExporterStructure() {
        CompositeTerrainExporter exporter = new CompositeTerrainExporter();
        assertDoesNotThrow(() -> exporter.addExporter((terrain, biomes, path) -> {}));
        assertDoesNotThrow(() -> exporter.export(
                new TerrainData(2, 2, 10),
                new JsonBiome[2][2],
                "mock_dir"
        ));
    }
}
