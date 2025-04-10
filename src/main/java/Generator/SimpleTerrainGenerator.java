package Generator;

import Generator.Interfaces.ITerrainGenerator;
import java.util.Random;

public class SimpleTerrainGenerator implements ITerrainGenerator {
    private TerrainConfig config;

    public SimpleTerrainGenerator(TerrainConfig config) {
        this.config = config;
    }

    @Override
    public TerrainData generate() {
        TerrainData data = new TerrainData(config.width, config.height);
        Random rand = new Random();

        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                float height = rand.nextFloat();  // Random elevation
                data.setHeight(x, y, height);
            }
        }

        return data;
    }
}
