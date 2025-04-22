
package Generator.Options;

public class TerrainConfig {
    public int width;
    public int height;
    public int depth = 256;
    public String biomeDirectory;
    public String featureDirectory;
    public String biomePlacementPath;
    public String featurePlacementPath;
    public String generatorType = "simple";
    public long seed = System.currentTimeMillis();
}
