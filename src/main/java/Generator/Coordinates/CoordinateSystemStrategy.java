package Generator.Coordinates;

import Generator.Interfaces.ICoordinateSystem;
import Generator.Options.CoordinateSystemType;

// Unused while explicit switch case is in effect
public class CoordinateSystemStrategy {
    public static ICoordinateSystem create(CoordinateSystemType type) {
        return switch (type) {
            case DISCRETE -> new DiscreteCoordinateSystem();
            case CONTINUOUS -> new ContCoordinateSystem();
        };
    }
}
