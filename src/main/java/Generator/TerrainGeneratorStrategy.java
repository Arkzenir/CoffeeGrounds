package Generator;

import Generator.Interfaces.ICoordinateSystem;
import Generator.Interfaces.ITerrainGenerator;
import Generator.Options.TerrainConfig;

// Unused while explicit switch case is in effect
public class TerrainGeneratorStrategy {
    public static ITerrainGenerator create(TerrainConfig config, ICoordinateSystem coordSystem) {
        return switch (config.generatorType) {
            case SIMPLE -> new SimpleTerrainGenerator(config, coordSystem);
            case PERLIN -> new PerlinTerrainGenerator(config, coordSystem);
        };
    }
}