
package Generator.Placements;

public class FeaturePlacement {
    public String file;
    public int x;
    public int y;
    public Float minElevation;
    public Float maxElevation;

    public boolean isValid(float elevation) {
        if (minElevation != null && elevation < minElevation) return false;
        if (maxElevation != null && elevation > maxElevation) return false;
        return true;
    }
}
