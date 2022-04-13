package sk.uniza.fri.hadik.objekty;

import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

public class Jablko extends Jedlo {
    public Jablko(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY) {
        super(manazerZdrojov, poziciaX, poziciaY, TypTextury.JABLKO, 1, 0, 8);
    }
}
