package Generator.Options;

import com.fasterxml.jackson.annotation.JsonSetter;

public class TerrainConfig {
    public int width;
    public int height;
    public int depth = 256;
    public String biomeDirectory;
    public String featureDirectory;
    public String biomePlacementPath;
    public String featurePlacementPath;
    public GeneratorType generatorType = GeneratorType.SIMPLE;
    public long seed = System.currentTimeMillis();

    //Set seed randomly if none or zero
    @JsonSetter("seed")
    public void setSeed(long seed) {
        this.seed = seed != 0L ? seed : System.currentTimeMillis();
    }
}
