package sk.uniza.fri.hadik.objekty;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.uniza.fri.hadik.HraHadik;
import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;
import sk.uniza.fri.hadik.zdroje.TypTextury;

public abstract class HernyObjekt {
    private final ManazerZdrojov manazerZdrojov;
    private Texture textura;

    private int poziciaX;
    private int poziciaY;

    public HernyObjekt(ManazerZdrojov manazerZdrojov, int poziciaX, int poziciaY, TypTextury typTextury) {
        this.manazerZdrojov = manazerZdrojov;
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.textura = manazerZdrojov.getTextura(typTextury);
    }

    public Texture getTextura() {
        return textura;
    }

    protected void setTextura(Texture textura) {
        this.textura = textura;
    }

    protected ManazerZdrojov getManazerZdrojov() {
        return manazerZdrojov;
    }

    public int getPoziciaX() {
        return poziciaX;
    }

    public int getPoziciaY() {
        return poziciaY;
    }

    public void setPoziciaX(int poziciaX) {
        this.poziciaX = poziciaX;
    }

    public void setPoziciaY(int poziciaY) {
        this.poziciaY = poziciaY;
    }

    public void vykresli(SpriteBatch batch) {
        batch.draw(this.textura, this.poziciaX, this.poziciaY, HraHadik.VELKOST_POLICKA, HraHadik.VELKOST_POLICKA);
    }

    public boolean kolizia(int x, int y) {
        return this.poziciaX == x && this.poziciaY == y;
    }
}
