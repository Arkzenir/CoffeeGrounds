package Utils;

import Generator.Biomes.JsonBiome;
import Generator.TerrainData;
import Utils.Interfaces.ITerrainExporter;

import java.io.FileWriter;
import java.io.IOException;

public class HeightmapAsTextExporter implements ITerrainExporter {
    @Override
    public void export(TerrainData data, JsonBiome[][] biome, String directoryPath) {
        try (FileWriter writer = new FileWriter(directoryPath + "/heightmap.txt")) {
            for (int y = 0; y < data.getHeight(); y++) {
                for (int x = 0; x < data.getWidth(); x++) {
                    float value = data.getDepthAt(x, y);
                    if (Math.abs(value - Math.round(value)) < 0.001f) {
                        writer.write(String.format("%d", Math.round(value)));
                    } else {
                        writer.write(String.format("%.2f", value));
                    }
                    if (x < data.getWidth() - 1) writer.write(",");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
