package Utils;

import Generator.Biomes.JsonBiome;
import Generator.TerrainData;
import Utils.Interfaces.ITerrainExporter;

import java.io.FileWriter;
import java.io.IOException;

public class BiomeAsTextExporter implements ITerrainExporter {
    @Override
    public void export(TerrainData data, JsonBiome[][] biome, String directoryPath) {
        try (FileWriter writer = new FileWriter(directoryPath + "/biomes.txt")) {
            for (int y = 0; y < data.getHeight(); y++) {
                for (int x = 0; x < data.getWidth(); x++) {
                    String name = biome[x][y] != null ? biome[x][y].getName() : "Unknown";
                    writer.write(name + (x < data.getWidth() - 1 ? "," : ""));
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
