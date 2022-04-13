package sk.uniza.fri.hadik.objekty;

import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

public class Zaba extends PohybliveJedlo {
    public Zaba(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY) {
        super(manazerZdrojov, poziciaX, poziciaY, TypTextury.ZABA, 0.50f, Smer.nahodnySmer(), -1, 0.10f, 20);
    }
}
