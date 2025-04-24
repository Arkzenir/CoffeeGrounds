package Generator;

import Generator.Interfaces.ICoordinateSystem;
import Generator.Interfaces.ITerrainGenerator;
import Generator.Options.TerrainConfig;

public class PerlinTerrainGenerator implements ITerrainGenerator {
    private final TerrainConfig config;
    private final PerlinNoise perlin;
    private final ICoordinateSystem coordinateSystem;

    public PerlinTerrainGenerator(TerrainConfig config, ICoordinateSystem coordinateSystem) {
        this.config = config;
        this.perlin = new PerlinNoise(config.seed);
        this.coordinateSystem = coordinateSystem;
    }


    @Override
    public TerrainData generate() {
        TerrainData data = new TerrainData(config.width, config.height, config.depth, coordinateSystem);

        // Increase scale for discrete systems to force more noise variability
        float scale = (coordinateSystem instanceof Generator.Coordinates.DiscreteCoordinateSystem) ? 0.2f : 0.1f;

        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                float worldX = coordinateSystem.toWorldX(x) * scale;
                float worldY = coordinateSystem.toWorldY(y) * scale;

                float noise = perlin.noise(worldX, worldY); // 0..1
                float heightF = (noise - 0.5f) * config.depth;

                // Snap to .0 for discrete terrain
                if (coordinateSystem instanceof Generator.Coordinates.DiscreteCoordinateSystem) {
                    heightF = (float) Math.floor(heightF);
                }

                data.setHeight(x, y, heightF);
            }
        }

        return data;
    }
}
