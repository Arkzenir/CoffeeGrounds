package Utils;

import Generator.Biomes.JsonBiome;
import Generator.TerrainData;
import Utils.Interfaces.ITerrainExporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TerrainAsImageExporter implements ITerrainExporter {
    @Override
    public void export(TerrainData data, JsonBiome[][] biome, String directoryPath) {
        int width = data.getWidth();
        int height = data.getHeight();

        float min = Float.MAX_VALUE, max = -Float.MAX_VALUE;
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                float v = data.getDepthAt(x, y);
                if (v < min) min = v;
                if (v > max) max = v;
            }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float value = data.getDepthAt(x, y);
                Color biomeColor = (biome[x][y] != null) ? biome[x][y].getColor() : Color.MAGENTA;
                Color adjustedColor = adjustBrightness(biomeColor, value, min, max);
                image.setRGB(x, y, adjustedColor.getRGB());
            }
        }

        try {
            File outputFile = new File(directoryPath + "/terrain_as_image.png");
            outputFile.getParentFile().mkdirs();
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Color adjustBrightness(Color color, double value, double min, double max) {
        double normalized = 2.0 * ((value - min) / (max - min)) - 1.0;
        normalized = Math.max(-1.0, Math.min(1.0, normalized));

        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float brightness = hsb[2] + (float) (normalized * 0.2f);
        brightness = Math.max(0f, Math.min(1f, brightness));

        return Color.getHSBColor(hsb[0], hsb[1], brightness);
    }
}