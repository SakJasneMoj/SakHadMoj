package sk.uniza.fri.hadik.objekty;

import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

public class PohybliveJedlo extends PohyblivyHernyObjekt implements IJedlo {
    private int zvacsenieChvostaHadika;
    private float zrychlenieHadika;
    private float dobaZmiznutia;

    public PohybliveJedlo(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY, TypTextury typTextury,
                          float rychlostPohybu, Smer smerPohybu, int zvacsenieChvostaHadika, float zrychlenieHadika, float dobaZmiznutia) {
        super(manazerZdrojov, poziciaX, poziciaY, typTextury, rychlostPohybu, smerPohybu);
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

    public void setZvacsenieChvostaHadika(int zvacsenieChvostaHadika) {
        this.zvacsenieChvostaHadika = zvacsenieChvostaHadika;
    }

    @Override
    public void odcitajDobu(float deltaTime) {
        this.dobaZmiznutia -= deltaTime;
    }
}
