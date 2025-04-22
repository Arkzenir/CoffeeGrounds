package Generator.Biomes;

import Generator.Interfaces.IAddition;
import java.awt.Color;

public class JsonBiome implements IAddition {

    public String color; // hex code like "#00FF00"
    public String name;

    public Color getColor() {
        return Color.decode(color);
    }
}
