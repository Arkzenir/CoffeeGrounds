package Utils;

import Generator.Biomes.JsonBiome;
import Generator.TerrainData;
import Utils.Interfaces.ITerrainExporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class BiomeAsJsonExporter implements ITerrainExporter {
    @Override
    public void export(TerrainData data, JsonBiome[][] biome, String directoryPath) {
        try {
            String[][] output = new String[data.getWidth()][data.getHeight()];
            for (int x = 0; x < data.getWidth(); x++) {
                for (int y = 0; y < data.getHeight(); y++) {
                    output[x][y] = biome[x][y] != null ? biome[x][y].getName() : "Unknown";
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(directoryPath + "/biomes.json"), output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
