package sk.uniza.fri.hadik.objekty;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import sk.uniza.fri.hadik.HraHadik;
import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

import java.util.LinkedList;

public class Hadik extends PohyblivyHernyObjekt {
    private Texture texturaChvosta;

    private LinkedList<GridPoint2> chvost;

    private boolean mrtvy;
    private float powerUp;

    public Hadik(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY) {
        super(manazerZdrojov, poziciaX, poziciaY, TypTextury.HLAVA, 0.25f, Smer.VPRAVO);
        this.texturaChvosta = manazerZdrojov.getTextura(TypTextury.CHVOST);
        this.chvost = new LinkedList<>();
        this.chvost.add(new GridPoint2(poziciaX, poziciaY));
        this.chvost.add(new GridPoint2(poziciaX, poziciaY));
        this.chvost.add(new GridPoint2(poziciaX, poziciaY));
        this.chvost.add(new GridPoint2(poziciaX, poziciaY));
        this.chvost.add(new GridPoint2(poziciaX, poziciaY));
        this.chvost.add(new GridPoint2(poziciaX, poziciaY));

    }

    @Override
    public boolean posun(float deltaTime) {
        // TODO: V pripade posunu, aktualizujte (posunte) chvost
        int poziciaHlavyX = super.getPoziciaX();
        int poziciaHlavyY = super.getPoziciaY();



        if(super.posun(deltaTime)) {
            this.powerUp --;
            // odstranenie posledneho prvku
            this.chvost.removeLast();
            this.chvost.addFirst(new GridPoint2(poziciaHlavyX, poziciaHlavyY));
            // pridanie prveho - novy gridpoint

            if (this.koliziaChvost(super.getPoziciaX(), super.getPoziciaY())) {
                this.setMrtvy(true);

                return true;

            }
        }
        return false;
    }

    @Override
    public void vykresli(SpriteBatch batch) {
        // TODO: Vykreslite chvost a hlavu
        if (!this.mrtvy) {
            this.texturaChvosta = super.getManazerZdrojov().getTextura(TypTextury.CHVOST);
            this.setTextura(super.getManazerZdrojov().getTextura(TypTextury.HLAVA));
        }

        for (GridPoint2 clanokChvostu : this.chvost ) {
            batch.draw(this.texturaChvosta, clanokChvostu.x, clanokChvostu.y, HraHadik.VELKOST_POLICKA, HraHadik.VELKOST_POLICKA);
        }

        super.vykresli(batch);
    }

    public void zmenSmer(Smer smer) {
        if( super.getSmerPohybu() == smer) {
            return;
        }
        if(
                (super.getSmerPohybu() == Smer.VLAVO && smer != Smer.VPRAVO) ||
                        (super.getSmerPohybu() == Smer.VPRAVO && smer != Smer.VLAVO) ||
                        (super.getSmerPohybu() == Smer.HORE && smer != Smer.DOLE) ||
                        (super.getSmerPohybu() == Smer.DOLE && smer != Smer.HORE)
        ) {
            super.setSmerPohybu(smer);
        }
    }

    public boolean koliziaChvost(int x, int y) {
        for (GridPoint2 clanokChvostu : this.chvost) {
            if (x == clanokChvostu.x && y == clanokChvostu.y) {
                return true;
            }
        }
        return false;
    }

    public boolean zjedz(HernyObjekt hernyObjekt) {
        // TODO: Zistite, ci herny objekt je IJedlo - ak ano, nastavte rychlost pohybu a zvacsenie / zmensenie chvosta
        // Ak nie je herny objekt jedlo, nastavte hadika na mrtveho
        if (hernyObjekt instanceof IJedlo) {
            IJedlo jedlo = (IJedlo) hernyObjekt;
            this.setRychlostPohybu(this.getRychlostPohybu() + jedlo.getZrychlenieHadika());
            if (jedlo.getZvacsenieChvostaHadika() < 0) {
                for (int i = 0; i > jedlo.getZvacsenieChvostaHadika(); i--) {
                    this.chvost.removeLast();
                    if (this.chvost.size() <= 1) {
                        this.setMrtvy(true);
                        return false;
                    }
                }
            } else {
                for (int i = 0; i < jedlo.getZvacsenieChvostaHadika(); i++) {
                    this.chvost.addLast(new GridPoint2(super.getPoziciaX(), super.getPoziciaY()));
                }

                if (hernyObjekt instanceof Mrkva) {
                    this.powerUp = 20;


                }
            }
            // zvacsovanie chvosta
            // false iba ak bude moc kratky
        } else { // inak narazil do steny
            if (this.powerUp <= 0) {
                this.setMrtvy(true);
                return false;
            }


        }

        return true;
    }

    public boolean isMrtvy() {
        return this.mrtvy;
    }

    public void setMrtvy(boolean mrtvy) {
        this.mrtvy = mrtvy;

        if (mrtvy) {
            this.setTextura(this.getManazerZdrojov().getTextura(TypTextury.HLAVA_MRTVA));
        } else {
            this.setTextura(this.getManazerZdrojov().getTextura(TypTextury.HLAVA));
        }
    }
}
