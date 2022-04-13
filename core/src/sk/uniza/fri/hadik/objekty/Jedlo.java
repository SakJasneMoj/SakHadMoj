package sk.uniza.fri.hadik.objekty;

import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

public abstract class Jedlo extends HernyObjekt implements IJedlo {
    private int zvacsenieChvostaHadika;
    private float zrychlenieHadika;
    private float dobaZmiznutia;

    public Jedlo(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY, TypTextury typTextury,
                 int zvacsenieChvostaHadika, float zrychlenieHadika, float dobaZmiznutia) {
        super(manazerZdrojov, poziciaX, poziciaY, typTextury);
        this.zvacsenieChvostaHadika = zvacsenieChvostaHadika;
        this.zrychlenieHadika = zrychlenieHadika;
        this.dobaZmiznutia = dobaZmiznutia;
    }

    @Override
    public int getZvacsenieChvostaHadika() {
        return this.zvacsenieChvostaHadika;
    }

    @Override
    public float getZrychlenieHadika() {
        return this.zrychlenieHadika;
    }

    @Override
    public float getDobaZmiznutia() {
        return this.dobaZmiznutia;
    }

    @Override
    public void odcitajDobu(float deltaTime) {
        this.dobaZmiznutia -= deltaTime;
    }
}
