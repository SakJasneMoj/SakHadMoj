package sk.uniza.fri.hadik.zdroje;

import com.badlogic.gdx.graphics.Texture;

public enum TypTextury {
    HLAVA("hlava.png"),
    HLAVA_MRTVA("hlava-mrtva.png"),
    CHVOST("chvost.png"),
    JABLKO("jablko.png"),
    LENOCHOD("lenochod.png"),
    KURIATKO("kuriatko.png"),
    ZABA("zaba.png"),
    MRKVA("mrkva.png"),
    STENA("stena.png"),
    KURA("kura.png");


    private String nazovSuboru;

    TypTextury(String nazovSuboru) {

        this.nazovSuboru = nazovSuboru;
    }

    public String getNazovSuboru() {
        return this.nazovSuboru;
    }




}
