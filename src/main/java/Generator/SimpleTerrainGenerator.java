package Generator;

import Generator.Interfaces.ITerrainGenerator;
import Generator.Options.TerrainConfig;

import java.util.Random;

public class SimpleTerrainGenerator implements ITerrainGenerator {
    private TerrainConfig config;
    private Random rand;

    public SimpleTerrainGenerator(TerrainConfig config) {
        this.config = config;
        this.rand = new Random(config.seed);
    }

    @Override
    public TerrainData generate() {
        TerrainData data = new TerrainData(config.width, config.height);

        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                float height = (rand.nextFloat() - 0.5f) * config.depth;  // Random elevation
                data.setHeight(x, y, height);
            }
        }

        return data;
    }
}
