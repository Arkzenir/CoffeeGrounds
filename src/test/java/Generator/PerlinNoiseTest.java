package Generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PerlinNoiseTest {

    @Test
    void testPerlinNoiseDeterminism() {
        PerlinNoise noise1 = new PerlinNoise(42);
        PerlinNoise noise2 = new PerlinNoise(42);
        assertEquals(noise1.noise(0.5f, 0.5f), noise2.noise(0.5f, 0.5f), 0.0001);
    }

    @Test
    void testPerlinNoiseOutputBounds() {
        PerlinNoise noise = new PerlinNoise(42);
        float value = noise.noise(0.1f, 0.1f);
        assertTrue(value >= 0.0f && value <= 1.0f);
    }
}
