package Generator;

import Generator.Interfaces.ICoordinateSystem;

public class TerrainData {
    public float[][] heightMap;
    private final int width;
    private final int height;
    private final int depth;
    private final ICoordinateSystem coordinateSystem;

    public TerrainData(int width, int height, int depth, ICoordinateSystem coordinateSystem) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.coordinateSystem = coordinateSystem;
        this.heightMap = new float[width][height];
    }

    public float toWorldX(int x) {
        return coordinateSystem.toWorldX(x);
    }
    public float toWorldY(int y) {
        return coordinateSystem.toWorldY(y);
    }
    public float toWorldZ(int z) {
        return coordinateSystem.toWorldZ(z);
    }

    public void setHeight(int x, int y, float value) {
        heightMap[x][y] = value;
    }

    public float getHeight(int x, int y) {
        return heightMap[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}