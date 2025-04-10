package Generator;

import java.util.Random;

public class PerlinNoise {
    private int[] permutation;

    public PerlinNoise(long seed) {
        permutation = new int[512];
        Random rand = new Random(seed);
        int[] p = new int[256];
        for (int i = 0; i < 256; i++) p[i] = i;
        for (int i = 0; i < 256; i++) {
            int j = rand.nextInt(256);
            int tmp = p[i];
            p[i] = p[j];
            p[j] = tmp;
        }
        for (int i = 0; i < 512; i++) {
            permutation[i] = p[i % 256];
        }
    }

    public float noise(float x, float y) {
        int xi = (int) Math.floor(x) & 255;
        int yi = (int) Math.floor(y) & 255;

        float xf = x - (int) Math.floor(x);
        float yf = y - (int) Math.floor(y);

        float u = fade(xf);
        float v = fade(yf);

        int aa = permutation[permutation[xi] + yi];
        int ab = permutation[permutation[xi] + yi + 1];
        int ba = permutation[permutation[xi + 1] + yi];
        int bb = permutation[permutation[xi + 1] + yi + 1];

        float x1 = lerp(grad(aa, xf, yf), grad(ba, xf - 1, yf), u);
        float x2 = lerp(grad(ab, xf, yf - 1), grad(bb, xf - 1, yf - 1), u);

        return (lerp(x1, x2, v) + 1) / 2; // normalize to [0,1]
    }

    private float fade(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private float lerp(float a, float b, float x) {
        return a + x * (b - a);
    }

    private float grad(int hash, float x, float y) {
        switch (hash & 0x3) {
            case 0: return x + y;
            case 1: return -x + y;
            case 2: return x - y;
            case 3: return -x - y;
            default: return 0; // Should never happen
        }
    }
}
