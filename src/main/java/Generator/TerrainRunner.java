
package Generator;

import Generator.Biomes.JsonBiome;
import Generator.Features.JsonFeature;
import Generator.Interfaces.ITerrainGenerator;
import Generator.Options.*;
import Generator.Placements.*;
import Utils.PlacementUtils;
import Utils.TerrainAsImageExporter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.*;

public class TerrainRunner {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //For better jar generation
        InputStream configStream = TerrainRunner.class.getClassLoader().getResourceAsStream("config.json");
        TerrainConfig config = mapper.readValue(configStream, TerrainConfig.class);
        Random random = new Random(config.seed);

        ITerrainGenerator generator = switch (config.generatorType.toLowerCase()) {
            case "perlin" -> new PerlinTerrainGenerator(config);
            case "simple" -> new SimpleTerrainGenerator(config);
            default -> throw new IllegalArgumentException("Unknown generator: " + config.generatorType);
        };

        TerrainData terrain = generator.generate();

        // --- Biome Masking ---
        JsonBiome[][] biomeMask = new JsonBiome[config.width][config.height];
        Map<String, JsonBiome> biomeMap = new HashMap<>();
        InputStream biomeStream = TerrainRunner.class.getClassLoader().getResourceAsStream(config.biomePlacementPath);
        BiomePlacementConfig biomeConfig = mapper.readValue(biomeStream, BiomePlacementConfig.class);
        for (BiomePlacement rule : biomeConfig.biomeRules) {
            String path = "biomes/" + rule.file;
            JsonBiome biome = biomeMap.computeIfAbsent(path, p -> BiomeFactory.loadFromFile(p));
            PlacementArea clipped = PlacementUtils.clipAreaToBounds(rule.area, config.width, config.height);
            if (clipped == null) continue;

            for (int x = clipped.x; x < clipped.x + clipped.width; x++) {
                for (int y = clipped.y; y < clipped.y + clipped.height; y++) {
                    biomeMask[x][y] = biome;
                }
            }
        }

        // --- Feature Masking ---
        InputStream featureStream = TerrainRunner.class.getClassLoader().getResourceAsStream(config.featurePlacementPath);
        FeaturePlacementConfig featureConfig = mapper.readValue(featureStream, FeaturePlacementConfig.class);
        for (FeaturePlacement placement : featureConfig.features) {
            if (!PlacementUtils.isPositionInBounds(placement.x, placement.y, config.width, config.height)) continue;
            float elevation = terrain.heightMap[placement.x][placement.y];
            if (!placement.isValid(elevation)) continue;

            JsonFeature feature = FeatureFactory.loadFromFile("features/" + placement.file);
            feature.applyFeature(terrain.heightMap, placement.x, placement.y);
        }

        JsonBiome defaultBiome = biomeMask[0][0];  // fallback
        TerrainAsImageExporter exporter = new TerrainAsImageExporter();
        exporter.export(terrain, biomeMask, "output/terrain.png");

        System.out.println("Terrain generation complete.");
    }
}
