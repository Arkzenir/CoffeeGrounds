package Generator;

public class TerrainData {
    public int[][] heightMap;
    private final int width;
    private final int height;
    private final int maxDepth;

    public TerrainData(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.maxDepth = depth;
        this.heightMap = new int[width][height];
    }

    public void setDepthAt(int x, int y, int value) {
        if (value < 0)
            value = Math.max(value, -1* maxDepth);
        else
            value = Math.min(value, maxDepth);
        heightMap[x][y] = value;
    }

    public int getDepthAt(int x, int y) {
        return heightMap[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaxDepth() { return maxDepth; }
}