// Heightmap Monochrome Image Exporter
package Utils;

import Generator.Biomes.JsonBiome;
import Generator.TerrainData;
import Utils.Interfaces.ITerrainExporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HeightmapAsImageExporter implements ITerrainExporter {
    @Override
    public void export(TerrainData data, JsonBiome[][] biome, String directoryPath) {
        int width = data.getWidth();
        int height = data.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        float min = Float.MAX_VALUE;
        float max = -Float.MAX_VALUE;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float val = data.getDepthAt(x, y);
                if (val < min) min = val;
                if (val > max) max = val;
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float value = data.getDepthAt(x, y);
                int intensity = (int) (255 * (value - min) / (max - min));
                Color gray = new Color(intensity, intensity, intensity);
                image.setRGB(x, y, gray.getRGB());
            }
        }

        try {
            ImageIO.write(image, "png", new File(directoryPath + "/heightmap.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
