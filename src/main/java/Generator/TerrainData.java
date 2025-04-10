package Generator;

public class TerrainData {
    public float[][] heightMap;

    public TerrainData(int width, int height) {
        heightMap = new float[width][height];
    }

    public void setHeight(int x, int y, float value) {
        heightMap[x][y] = value;
    }

    public float getHeight(int x, int y) {
        return heightMap[x][y];
    }

    public int getWidth() {
        return heightMap.length;
    }

    public int getHeight() {
        return heightMap[0].length;
    }
}
