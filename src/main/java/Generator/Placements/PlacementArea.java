
package Generator.Placements;

public class PlacementArea {
    public int x;
    public int y;
    public int width;
    public int height;

    public boolean contains(int px, int py) {
        return px >= x && px < (x + width) && py >= y && py < (y + height);
    }
}
