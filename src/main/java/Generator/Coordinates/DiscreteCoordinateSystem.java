package Generator.Coordinates;

import Generator.Interfaces.ICoordinateSystem;

public class DiscreteCoordinateSystem implements ICoordinateSystem {
    public float toWorldX(int x) { return x; }
    public float toWorldY(int y) { return y; }
    public float toWorldZ(int z) { return z; }
}