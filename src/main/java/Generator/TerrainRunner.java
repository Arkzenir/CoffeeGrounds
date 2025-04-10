package Generator;

import Generator.Biomes.JsonBiome;
import Generator.Features.JsonFeature;
import Utils.TerrainImageExporter;
import Generator.Interfaces.ITerrainGenerator;

public class TerrainRunner {
    public static void main(String[] args) {
        TerrainConfig config = new TerrainConfig(100, 100);
        ITerrainGenerator generator = new SimpleTerrainGenerator(config);
        TerrainData terrain = generator.generate();

        // Load biome and feature from JSON
        JsonBiome biome = BiomeFactory.loadFromFile("resources/biomes/grassland.json");
        JsonFeature hill = FeatureFactory.loadFromFile("resources/features/hill.json");

        // Apply a hill at the center
        hill.applyFeature(terrain.heightMap, config.width / 2, config.height / 2);

        TerrainImageExporter exporter = new TerrainImageExporter();
        exporter.export(terrain, biome, "output/terrain.png");

        System.out.println("Terrain generation complete.");
    }
}
