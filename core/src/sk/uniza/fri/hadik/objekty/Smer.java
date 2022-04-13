package sk.uniza.fri.hadik.objekty;

import java.util.Random;

public enum Smer {
    BEZ_POHYBU(0, 0),
    VPRAVO(1, 0),
    VPRAVO_DOLE(1, -1),
    VPRAVO_HORE(1, 1),

    VLAVO(-1, 0),
    VLAVO_DOLE(-1, -1),
    VLAVO_HORE(-1, 1),

    HORE(0, 1),
    DOLE(0, -1);

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Smer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Smer nahodnySmer() {
        Smer[] vsetkySmery = Smer.values();

        Random random = new Random();
        int nahodnyIndex = random.nextInt(vsetkySmery.length);
        return vsetkySmery[nahodnyIndex];
    }
}
