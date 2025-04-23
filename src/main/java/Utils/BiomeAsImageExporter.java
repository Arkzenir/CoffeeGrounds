package Utils;

import Generator.Biomes.JsonBiome;
import Generator.TerrainData;
import Utils.Interfaces.ITerrainExporter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

public class BiomeAsImageExporter implements ITerrainExporter {
    @Override
    public void export(TerrainData data, JsonBiome[][] biome, String directoryPath) {
        try {
            BufferedImage image = new BufferedImage(data.getWidth(), data.getHeight(), BufferedImage.TYPE_INT_RGB);
            HashMap<Color, String> legend = new HashMap<>();

            for (int x = 0; x < data.getWidth(); x++) {
                for (int y = 0; y < data.getHeight(); y++) {
                    JsonBiome b = biome[x][y];
                    Color color = b != null ? b.getColor() : Color.MAGENTA;
                    legend.put(color, b != null ? b.getName() : "Unknown");
                    image.setRGB(x, y, color.getRGB());
                }
            }

            ImageIO.write(image, "png", new File(directoryPath + "/biomes_colored.png"));
            try (PrintWriter legendWriter = new PrintWriter(new FileWriter(directoryPath + "/biomes_colored.legend.txt"))) {
                for (var entry : legend.entrySet()) {
                    Color c = entry.getKey();
                    legendWriter.printf("%s: #%02x%02x%02x\n", entry.getValue(), c.getRed(), c.getGreen(), c.getBlue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}