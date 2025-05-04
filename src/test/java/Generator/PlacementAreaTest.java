package Generator;

import Generator.Placements.PlacementArea;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlacementAreaTest {

    @Test
    void testContainsLogic() {
        PlacementArea area = new PlacementArea();
        area.x = 2;
        area.y = 3;
        area.width = 5;
        area.height = 4;

        assertTrue(area.contains(4, 5));
        assertFalse(area.contains(1, 2));
    }

    @Test
    void testBorderInclusion() {
        PlacementArea area = new PlacementArea();
        area.x = 0; area.y = 0; area.width = 3; area.height = 3;

        assertTrue(area.contains(2, 2));
        assertFalse(area.contains(3, 3));
    }
}
