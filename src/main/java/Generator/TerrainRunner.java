
package Generator;

import Generator.Biomes.JsonBiome;
import Generator.Features.JsonFeature;
import Generator.Interfaces.ITerrainGenerator;
import Generator.Options.*;
import Generator.Placements.*;
import Utils.TerrainImageExporter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
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

        List<JsonBiome> biomePool = new ArrayList<>();
        Map<String, JsonBiome> biomeMap = new HashMap<>();
        if (config.biomePlacementPath != null) {

            InputStream biomeStream = TerrainRunner.class.getClassLoader()
                    .getResourceAsStream("biome_placement.json");
            BiomePlacementConfig biomeConfig = mapper.readValue(biomeStream, BiomePlacementConfig.class);

            for (BiomePlacement rule : biomeConfig.biomeRules) {
                String path = config.biomeDirectory + rule.file;
                JsonBiome biome = biomeMap.computeIfAbsent(path, p -> BiomeFactory.loadFromFile(p));
                biomePool.add(biome);
            }
        }

        if (config.featurePlacementPath != null) {
            InputStream featureStream = TerrainRunner.class.getClassLoader()
                    .getResourceAsStream("feature_placement.json");
            FeaturePlacementConfig featureConfig = mapper.readValue(featureStream, FeaturePlacementConfig.class);

            for (FeaturePlacement placement : featureConfig.features) {
                JsonFeature feature = FeatureFactory.loadFromFile(config.featureDirectory + placement.file);
                float elevation = terrain.heightMap[placement.x][placement.y];
                if (placement.isValid(elevation)) {
                    feature.applyFeature(terrain.heightMap, placement.x, placement.y);
                }
            }
        }

        JsonBiome biomeToRender = !biomePool.isEmpty() ? biomePool.get(0) : null;
        TerrainImageExporter exporter = new TerrainImageExporter();
        exporter.export(terrain, biomeToRender, "output/terrain.png");

        System.out.println("Terrain generation complete.");
    }
}
