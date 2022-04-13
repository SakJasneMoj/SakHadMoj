package sk.uniza.fri.hadik.objekty;

import com.badlogic.gdx.graphics.Texture;
import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

public class Sliepka extends PohybliveJedlo {

    private float casOdSpawnutia;
    private boolean dospela;

    public Sliepka(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY) {
        super(manazerZdrojov, poziciaX, poziciaY, TypTextury.KURIATKO, 0.50f, Smer.nahodnySmer(), 1, 0, 15);
        this.casOdSpawnutia = 0;
        this.dospela = false;
    }

    public void rast(float delta) {
        this.casOdSpawnutia += delta;
        if (this.casOdSpawnutia >= 10) {
            super.setTextura(this.getManazerZdrojov().getTextura(TypTextury.KURA));
            super.setZvacsenieChvostaHadika(2);


            this.dospela = true;
        }

    }

    public boolean isDospela() {
        return this.dospela;
    }
}
