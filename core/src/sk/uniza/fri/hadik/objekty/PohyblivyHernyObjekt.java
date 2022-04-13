package sk.uniza.fri.hadik.objekty;

import sk.uniza.fri.hadik.HraHadik;
import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

public abstract class PohyblivyHernyObjekt extends HernyObjekt implements IPohyblivy {
    private float rychlostPohybu;
    private Smer smerPohybu;
    private float uplynulyCas;

    public PohyblivyHernyObjekt(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY, TypTextury typTextury,
                                float rychlostPohybu, Smer smerPohybu) {
        super(manazerZdrojov, poziciaX, poziciaY, typTextury);
        this.rychlostPohybu = rychlostPohybu;
        this.smerPohybu = smerPohybu;
        this.uplynulyCas = 0;
    }

    public float getRychlostPohybu() {
        return rychlostPohybu;
    }

    public Smer getSmerPohybu() {
        return smerPohybu;
    }

    protected void setRychlostPohybu(float rychlostPohybu) {
        this.rychlostPohybu = rychlostPohybu;
    }

    protected void setSmerPohybu(Smer smerPohybu) {
        this.smerPohybu = smerPohybu;
    }

    @Override
    public boolean posun(float deltaTime) {
        this.uplynulyCas += deltaTime;
        if (this.uplynulyCas >= this.rychlostPohybu) {
            this.uplynulyCas -= this.rychlostPohybu;
            if (this.uplynulyCas < 0)
                this.uplynulyCas = 0;

            this.setPoziciaX((this.getPoziciaX() + this.smerPohybu.getX() + HraHadik.SIRKA) % HraHadik.SIRKA);
            this.setPoziciaY((this.getPoziciaY() + this.smerPohybu.getY() + HraHadik.VYSKA) % HraHadik.VYSKA);
            return true;
        }

        return false;
    }

}
