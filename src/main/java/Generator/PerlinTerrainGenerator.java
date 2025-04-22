package Generator;

import Generator.Interfaces.ITerrainGenerator;
import Generator.Options.TerrainConfig;

public class PerlinTerrainGenerator implements ITerrainGenerator {
    private TerrainConfig config;
    private PerlinNoise perlin;

    public PerlinTerrainGenerator(TerrainConfig config) {
        this.config = config;
        this.perlin = new PerlinNoise(config.seed);
    }

    @Override
    public TerrainData generate() {
        TerrainData data = new TerrainData(config.width, config.height);

        float scale = 0.1f;
        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                float noise = perlin.noise(x * scale, y * scale);
                noise = (noise - 0.5f) * config.depth;
                if (y%5 == 0) System.out.print("noise at " + y + ": " + noise);
                data.setHeight(x, y, noise);
            }
        }

        return data;
    }
}
