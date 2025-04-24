package Generator;

import Generator.Interfaces.ICoordinateSystem;
import Generator.Interfaces.ITerrainGenerator;
import Generator.Options.TerrainConfig;

import java.util.Random;

public class SimpleTerrainGenerator implements ITerrainGenerator {
    private final TerrainConfig config;
    private final Random rand;
    private final ICoordinateSystem coordinateSystem;

    public SimpleTerrainGenerator(TerrainConfig config, ICoordinateSystem coordinateSystem) {
        this.config = config;
        this.rand = new Random(config.seed);
        this.coordinateSystem = coordinateSystem;
    }

    @Override
    public TerrainData generate() {
        TerrainData data = new TerrainData(config.width, config.height, config.depth, coordinateSystem);

        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                float worldX = coordinateSystem.toWorldX(x);
                float worldY = coordinateSystem.toWorldY(y);
                float value = (rand.nextFloat() - 0.5f) * config.depth + (worldX + worldY) * 0.01f;

                // Snap to .0 if discrete
                if (coordinateSystem instanceof Generator.Coordinates.DiscreteCoordinateSystem) {
                    value = (float) Math.floor(value);
                }

                data.setHeight(x, y, value);
            }
        }

        return data;
    }
}