package Generator.Coordinates;

import Generator.Interfaces.ICoordinateSystem;

public class ContCoordinateSystem implements ICoordinateSystem {
    public float toWorldX(int x) { return (float) x + 0.5f; }
    public float toWorldY(int y) { return (float) y + 0.5f; }
    public float toWorldZ(int z) { return (float) z + 0.5f; }
}