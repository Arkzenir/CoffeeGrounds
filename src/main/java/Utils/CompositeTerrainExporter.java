package Utils;

import Generator.Biomes.JsonBiome;
import Generator.TerrainData;
import Utils.Interfaces.ITerrainExporter;

import java.util.ArrayList;
import java.util.List;

public class CompositeTerrainExporter implements ITerrainExporter {
    private final List<ITerrainExporter> exporters = new ArrayList<>();

    public void addExporter(ITerrainExporter exporter) {
        exporters.add(exporter);
    }

    @Override
    public void export(TerrainData data, JsonBiome[][] biome, String filePath) {
        for (ITerrainExporter exporter : exporters) {
            exporter.export(data, biome, filePath);
        }
    }
}
