# CoffeeGrounds

**CoffeeGrounds** is a modular Java-based procedural terrain generation tool that simulates simple heightmap-biome combinations using customizable biomes and terrain features.

## Features

* Support for multiple terrain generation algorithms (e.g., Perlin noise)
* Biome and feature definitions via JSON
* Flexible configuration for terrain and object placement
* Exportable outputs (images, text, JSON)

## Project Structure

```
main/
├── java/
│   └── Generator/
│       ├── Biomes/               # Biome definitions and factories
│       ├── Features/             # Terrain features like lakes, hills
│       ├── Interfaces/           # Core abstraction interfaces
│       ├── Options/              # Configuration models
│       ├── Placements/           # Placement logic
│       ├── Utils/                # Exporters for output
│       └── TerrainRunner.java    # Entry point
│
└── resources/
    ├── biomes/                  # JSON files defining biomes
    ├── features/                # JSON files defining features
    └── *.json                   # Configs for placements and terrain
```

## Getting Started

### Prerequisites

* Java 21
* Maven

### Build Instructions (Using Maven)

```bash
# Navigate to the root directory
cd coffee_grounds

# Compile and package the project (You must use Java 21)
mvn clean install

# Run the terrain generator using the internal config (resources/config.json)
java -jar target/CoffeeGrounds-1.0-SNAPSHOT.jar

# OR run with a custom config file path (Check the example config below)
java -jar target/CoffeeGrounds-1.0-SNAPSHOT.jar path/to/your/config.json
```

### Understanding the `pom.xml`

This project uses Maven for dependency management and build configuration.

* **Java Version**: Targets Java 21
* **Dependencies**: Includes Jackson for JSON parsing (`jackson-databind`, `jackson-annotations`)
## Configuration Example

The project uses JSON configuration files to control biome placement, feature distribution, and generator settings.

### Example config
```json
{
  "width": 200,
  "height": 100,
  "seed": 123456,
  "generatorType": "perlin",
  "biomeDirectory": "biomes/",
  "featureDirectory": "features/",
  "biomePlacementPath": "biome_placement.json",
  "featurePlacementPath": "feature_placement.json"
}
```

### Example: `resources/biome_placement.json`

```json
{
  "biomeRules": [
    {
      "file": "grassland.json",
      "area": { "x": 0, "y": 0, "width": 100, "height": 50 },
      "weight": 0.5
    },
    {
      "file": "desert.json",
      "area": { "x": 100, "y": 0, "width": 100, "height": 50 },
      "weight": 0.3
    },
    {
      "file": "forest.json",
      "area": { "x": 0, "y": 50, "width": 100, "height": 50 },
      "weight": 0.7
    },
    {
      "file": "tundra.json",
      "area": { "x": 100, "y": 50, "width": 100, "height": 50 },
      "weight": 0.2
    }
  ]
}
```

### Example: `resources/feature_placement.json`

```json
{
  "features": [
    { "file": "hill.json", "x": 30, "y": 30},
    { "file": "mountain.json", "x": 120, "y": 20},
    { "file": "crater.json", "x": 80, "y": 70},
    { "file": "lake.json", "x": 150, "y": 80},
    { "file": "plateau.json", "x": 50, "y": 50 }
  ]
}
```

### Example feature: Crater 

```json
{
  "name": "Crater",
  "radius": 14,
  "elevationBoost": -60,
  "flattenAmount": 0.1 (Optional)
}
```

### Example biome: Forest

```json
{
  "name": "Forest",
  "color": "#38761d"
}
```
## Exporting Output

Exporters are available in `Generator.Utils`, allowing terrain maps to be saved as:

* Images (heightmaps, biome maps)
* JSON
* Plain text

These can be triggered or extended within `TerrainRunner.java`, using the composite terrain exporter.

## Contributing

Contributions are welcome, feel free to open issues or submit pull requests

## License

MIT License

---

For questions or support, please contact the repository maintainer.
(This is an open source hobby project, so updates are not guaranteed)
