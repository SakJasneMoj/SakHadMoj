package sk.uniza.fri.hadik.objekty;

import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

public class Mrkva extends Jedlo {

    public Mrkva(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY) {
        super(manazerZdrojov, poziciaX, poziciaY, TypTextury.MRKVA, 0, 0, 10);
    }
}
