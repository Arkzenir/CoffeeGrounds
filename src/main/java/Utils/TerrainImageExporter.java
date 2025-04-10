package Utils;


import Generator.Biomes.JsonBiome;
import Generator.TerrainData;
import Utils.Interfaces.ITerrainExporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TerrainImageExporter implements ITerrainExporter {
    public void export(TerrainData data, JsonBiome biome, String filePath) {
        int width = data.getWidth();
        int height = data.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float h = data.getHeight(x, y);
                image.setRGB(x, y, biome.getColor(h).getRGB());
            }
        }

        try {
            File outputFile = new File(filePath);
            outputFile.getParentFile().mkdirs(); // Ensure folder exists
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
