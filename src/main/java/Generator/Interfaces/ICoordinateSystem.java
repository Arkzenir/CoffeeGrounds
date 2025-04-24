package Generator.Interfaces;

public interface ICoordinateSystem {
    float toWorldX(int x);
    float toWorldY(int y);
    float toWorldZ(int z);
}