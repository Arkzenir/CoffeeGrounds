package Generator;

public class TerrainData {
    public int[][] heightMap;
    private final int width;
    private final int height;
    private final int depth;

    public TerrainData(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.heightMap = new int[width][height];
    }

    public void setHeight(int x, int y, int value) {
        if (value < 0)
            value = Math.max(value, -1*depth);
        else
            value = Math.min(value, depth);
        heightMap[x][y] = value;
    }

    public int getHeight(int x, int y) {
        return heightMap[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}