package Generator.Features;

import Generator.Interfaces.IAddition;
import Generator.TerrainData;

public class JsonFeature implements IAddition {
    public String name;
    public int radius;
    public float elevationBoost;
    public Float flattenAmount = null;

    public void applyFeature(TerrainData terrain, int centerX, int centerY) {
        int width = terrain.getWidth();
        int height = terrain.getHeight();

        // Collect base height samples in the area
        int sampleCount = 0;
        int totalHeight = 0;
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                int x = centerX + dx;
                int y = centerY + dy;

                if (x >= 0 && x < width && y >= 0 && y < height) {
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    if (distance <= radius) {
                        totalHeight += terrain.getHeight(x, y);
                        sampleCount++;
                    }
                }
            }
        }

        int averageHeight = sampleCount > 0 ? totalHeight / sampleCount : 0;

        // Apply effect
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                int x = centerX + dx;
                int y = centerY + dy;

                if (x >= 0 && x < width && y >= 0 && y < height) {
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    if (distance <= radius) {
                        double falloff = 1.0 - (distance / radius);
                        double effect = falloff * elevationBoost;
                        int intEffect = (int) Math.round(effect);

                        int originalHeight = terrain.getHeight(x, y);
                        int baseHeight = originalHeight;

                        if (flattenAmount != null && flattenAmount > 0.0f) {
                            // Blend toward average height
                            baseHeight = (int) Math.round(
                                    (1 - flattenAmount) * originalHeight + flattenAmount * averageHeight
                            );
                        }

                        terrain.setHeight(x, y, baseHeight + intEffect);
                    }
                }
            }
        }
    }
}
