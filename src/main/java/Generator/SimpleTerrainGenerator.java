package Generator;

import Generator.Interfaces.ITerrainGenerator;
import Generator.Options.TerrainConfig;

import java.util.Random;

public class SimpleTerrainGenerator implements ITerrainGenerator {
    private final TerrainConfig config;
    private final Random rand;

    public SimpleTerrainGenerator(TerrainConfig config) {
        this.config = config;
        this.rand = new Random(config.seed);
    }

    @Override
    public TerrainData generate() {
        TerrainData data = new TerrainData(config.width, config.height, config.depth);
        int noiseRange = config.depth;
        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                int depth = rand.nextInt(noiseRange) - noiseRange / 2;
                data.setDepthAt(x, y, depth);
            }
        }

        return data;
    }
}