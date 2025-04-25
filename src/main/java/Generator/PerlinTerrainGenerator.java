package Generator;

import Generator.Interfaces.ITerrainGenerator;
import Generator.Options.TerrainConfig;

public class PerlinTerrainGenerator implements ITerrainGenerator {
    private final TerrainConfig config;
    private final PerlinNoise perlin;

    public PerlinTerrainGenerator(TerrainConfig config) {
        this.config = config;
        this.perlin = new PerlinNoise(config.seed);
    }


    @Override
    public TerrainData generate() {
        TerrainData data = new TerrainData(config.width, config.height, config.depth);

        float scale = 0.125f;

        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                float noise = perlin.noise(x * scale, y * scale);
                // Map noise in [0,1] to integer depth [-depth/2, +depth/2)
                int height = (int) ((noise - 0.5f) * config.depth);
                data.setHeight(x, y, height);
            }
        }

        return data;
    }
}
