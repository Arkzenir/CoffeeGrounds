package Generator;

import Generator.Biomes.*;
import Generator.Features.*;
import Generator.Options.*;
import Utils.TerrainImageExporter;
import Generator.Interfaces.ITerrainGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class TerrainRunner {
    public static void main(String[] args) throws Exception {
        // Config from config.json
        ObjectMapper TerrainMapper = new ObjectMapper();
        TerrainConfig config = TerrainMapper.readValue(new File("resources/config.json"), TerrainConfig.class);

        ITerrainGenerator generator;
        switch (config.generatorType.toLowerCase()) {
            case "perlin":
                generator = new PerlinTerrainGenerator(config);
                break;
            case "simple":
            default:
                generator = new SimpleTerrainGenerator(config);
                break;
        }

        TerrainData terrain = generator.generate();

        // Load and apply features from placement config
        ObjectMapper PlacementMapper = new ObjectMapper();
        GenerationOptions options = PlacementMapper.readValue(new File(config.placementConfigPath), GenerationOptions.class);

        for (FeaturePlacement placement : options.features) {
            JsonFeature feature = FeatureFactory.loadFromFile(config.featureDirectory + placement.file);
            feature.applyFeature(terrain.heightMap, placement.x, placement.y);
        }

        // Load one biome for rendering (or choose dynamically)
        List<JsonBiome> biomes = BiomeFactory.loadAllFromDirectory(config.biomeDirectory);
        JsonBiome biome = biomes.isEmpty() ? null : biomes.get(0);

        TerrainImageExporter exporter = new TerrainImageExporter();
        exporter.export(terrain, biome, "output/terrain.png");

        System.out.println("Terrain generation complete.");
    }
}
