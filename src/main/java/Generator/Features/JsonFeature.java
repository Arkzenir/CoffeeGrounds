package Generator.Features;

import Generator.Interfaces.IAddition;

public class JsonFeature implements IAddition {
    public String name;
    public int radius;
    public float elevationBoost;

    public void applyFeature(float[][] heightMap, int centerX, int centerY) {
        int width = heightMap.length;
        int height = heightMap[0].length;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                int dx = centerX + x;
                int dy = centerY + y;
                if (dx >= 0 && dx < width && dy >= 0 && dy < height) {
                    float distance = (float) Math.sqrt(x * x + y * y);
                    if (distance <= radius) {
                        float effect = (1 - (distance / radius)) * elevationBoost;
                        heightMap[dx][dy] = Math.min(1.0f, heightMap[dx][dy] + effect);
                    }
                }
            }
        }
    }
}
