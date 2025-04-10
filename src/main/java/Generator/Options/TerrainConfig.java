package Generator.Options;

public class TerrainConfig {
    public int width;
    public int height;
    public String biomeDirectory;
    public String featureDirectory;
    public String placementConfigPath;

    // generation mode, seed, etc.
    public String generatorType = "simple";
    public long seed = System.currentTimeMillis();  // Default to random

}
