package Utils;

import Generator.Placements.PlacementArea;

public class PlacementUtils {

    public static PlacementArea clipAreaToBounds(PlacementArea area, int terrainWidth, int terrainHeight) {
        int xStart = Math.max(0, area.x);
        int yStart = Math.max(0, area.y);
        int xEnd = Math.min(terrainWidth, area.x + area.width);
        int yEnd = Math.min(terrainHeight, area.y + area.height);

        int newWidth = xEnd - xStart;
        int newHeight = yEnd - yStart;

        if (newWidth <= 0 || newHeight <= 0) return null;

        PlacementArea clipped = new PlacementArea();
        clipped.x = xStart;
        clipped.y = yStart;
        clipped.width = newWidth;
        clipped.height = newHeight;

        return clipped;
    }

    public static boolean isPositionInBounds(int x, int y, int terrainWidth, int terrainHeight) {
        return x >= 0 && x < terrainWidth && y >= 0 && y < terrainHeight;
    }
}