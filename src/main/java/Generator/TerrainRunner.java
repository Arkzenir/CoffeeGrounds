
package Generator;

import Generator.Biomes.*;
import Generator.Coordinates.*;
import Generator.Features.*;
import Generator.Options.*;
import Generator.Placements.*;
import Utils.*;
import Generator.Interfaces.ICoordinateSystem;
import Generator.Interfaces.ITerrainGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TerrainRunner {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TerrainConfig config;
        Path basePath;

        // Load the config from CLI or fallback to internal resource
        if (args.length > 0) {
            Path configPath = Path.of(args[0]);
            config = mapper.readValue(Files.newInputStream(configPath), TerrainConfig.class);
            basePath = configPath.getParent();
        } else {
            InputStream configStream = TerrainRunner.class.getClassLoader().getResourceAsStream("config.json");
            if (configStream == null) {
                throw new FileNotFoundException("config.json not found in resources and no external config provided.");
            }
            config = mapper.readValue(configStream, TerrainConfig.class);
            basePath = Path.of(""); // current directory or empty since files will be internal
        }

        // Explicit switch for coordinate system (Better demonstration compared to strategy class)
        ICoordinateSystem coordinateSystem;
        switch (config.coordinateSystem) {
            case DISCRETE -> coordinateSystem = new DiscreteCoordinateSystem();
            case CONTINUOUS -> coordinateSystem = new ContCoordinateSystem();
            default -> throw new IllegalStateException("Unexpected value: " + config.coordinateSystem);
        }

        // Explicit switch for terrain generator (Better demonstration compared to strategy class)
        ITerrainGenerator generator;
        switch (config.generatorType) {
            case PERLIN -> generator = new PerlinTerrainGenerator(config, coordinateSystem);
            case SIMPLE -> generator = new SimpleTerrainGenerator(config, coordinateSystem);
            default -> throw new IllegalStateException("Unexpected value: " + config.generatorType);
        }

        TerrainData terrain = generator.generate();

        // --- Biome Masking ---
        JsonBiome[][] biomeMask = new JsonBiome[config.width][config.height];
        Map<String, JsonBiome> biomeMap = new HashMap<>();

        InputStream biomeStream = getInputStream(basePath, config.biomePlacementPath);
        BiomePlacementConfig biomeConfig = mapper.readValue(biomeStream, BiomePlacementConfig.class);
        for (BiomePlacement rule : biomeConfig.biomeRules) {
            String path = config.biomeDirectory + "/" + rule.file;
            JsonBiome biome = biomeMap.computeIfAbsent(path, p -> BiomeFactory.loadFromFile(basePath.resolve(p).toString()));
            PlacementArea clipped = PlacementUtils.clipAreaToBounds(rule.area, config.width, config.height);
            if (clipped == null) continue;

            for (int x = clipped.x; x < clipped.x + clipped.width; x++) {
                for (int y = clipped.y; y < clipped.y + clipped.height; y++) {
                    biomeMask[x][y] = biome;
                }
            }
        }

        // --- Feature Masking ---
        InputStream featureStream = getInputStream(basePath, config.featurePlacementPath);
        FeaturePlacementConfig featureConfig = mapper.readValue(featureStream, FeaturePlacementConfig.class);
        for (FeaturePlacement placement : featureConfig.features) {
            if (!PlacementUtils.isPositionInBounds(placement.x, placement.y, config.width, config.height)) continue;
            float elevation = terrain.heightMap[placement.x][placement.y];
            if (!placement.isValid(elevation)) continue;

            String path = config.featureDirectory + "/" + placement.file;
            JsonFeature feature = FeatureFactory.loadFromFile(basePath.resolve(path).toString());
            feature.applyFeature(terrain.heightMap, placement.x, placement.y);
        }

        JsonBiome defaultBiome = biomeMask[0][0]; // fallback

        // --- Exporting ---
        Path outputDir = Path.of("output");
        Files.createDirectories(outputDir);

        CompositeTerrainExporter exporter = new CompositeTerrainExporter();
        exporter.addExporter(new TerrainAsImageExporter());
        exporter.addExporter(new BiomeAsTextExporter());
        exporter.addExporter(new BiomeAsJsonExporter());
        exporter.addExporter(new BiomeAsImageExporter());
        exporter.addExporter(new HeightmapAsTextExporter());
        exporter.addExporter(new HeightmapAsImageExporter());

        exporter.export(terrain, biomeMask, outputDir.toString());

        System.out.println("Terrain generation complete.");
    }

    private static InputStream getInputStream(Path basePath, String filePath) throws IOException {
        Path resolvedPath = basePath.resolve(filePath);
        if (Files.exists(resolvedPath)) {
            return Files.newInputStream(resolvedPath);
        }
        InputStream internal = TerrainRunner.class.getClassLoader().getResourceAsStream(filePath);
        if (internal != null) return internal;

        throw new FileNotFoundException("File not found: " + filePath);
    }

}

