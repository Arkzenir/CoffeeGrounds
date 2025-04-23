package Utils.Interfaces;

import Generator.Biomes.JsonBiome;
import Generator.TerrainData;

public interface ITerrainExporter {
    void export(TerrainData data, JsonBiome[][] biome, String directoryPath);
}
