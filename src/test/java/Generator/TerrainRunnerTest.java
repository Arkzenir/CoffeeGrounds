package Generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TerrainRunnerTest {

    @Test
    void testTerrainRunnerRunsSuccessfullyWithoutArgs() {
        assertDoesNotThrow(() -> {
            TerrainRunner.main(new String[]{});
        });
    }
}
