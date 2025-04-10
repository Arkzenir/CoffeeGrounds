package Generator.Biomes;

import Generator.Interfaces.IBiome;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.awt.Color;
import java.util.List;

public class JsonBiome implements IBiome {
    public static class ElevationRange {
        public float min;
        public float max;
        public String color; // hex code like "#00FF00"

        public Color getColor() {
            return Color.decode(color);
        }
    }

    public String name;

    @JsonProperty("elevationRanges")
    public List<ElevationRange> elevationRanges;

    public Color getColor(float elevation) {
        for (ElevationRange range : elevationRanges) {
            if (elevation >= range.min && elevation < range.max) {
                return range.getColor();
            }
        }
        return Color.MAGENTA; // fallback color
    }
}
