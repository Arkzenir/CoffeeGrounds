package Util;

import Generator.Placements.PlacementArea;
import Utils.PlacementUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlacementUtilsTest {

    @Test
    void testClipping() {
        PlacementArea area = new PlacementArea();
        area.x = -5;
        area.y = -5;
        area.width = 15;
        area.height = 15;

        PlacementArea clipped = PlacementUtils.clipAreaToBounds(area, 10, 10);
        assertEquals(0, clipped.x);
        assertEquals(0, clipped.y);
        assertEquals(10, clipped.width);
        assertEquals(10, clipped.height);
    }
}
