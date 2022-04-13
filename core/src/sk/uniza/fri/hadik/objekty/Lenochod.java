package sk.uniza.fri.hadik.objekty;

import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

public class Lenochod extends PohybliveJedlo{

    public Lenochod(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY) {
        super(manazerZdrojov, poziciaX, poziciaY, TypTextury.LENOCHOD, 2, Smer.nahodnySmer(),
                2, -0.05f, 25);
    }
}
