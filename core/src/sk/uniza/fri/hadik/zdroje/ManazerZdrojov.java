package sk.uniza.fri.hadik.zdroje;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ManazerZdrojov {

    private AssetManager zdroje;

    public ManazerZdrojov() {
        this.zdroje = new AssetManager();

        for (TypTextury typTextury : TypTextury.values()) {
            this.zdroje.load(typTextury.getNazovSuboru(), Texture.class);
        }

        this.zdroje.finishLoading();
    }

    public Texture getTextura(TypTextury typTextury) {
        return this.zdroje.get(typTextury.getNazovSuboru(), Texture.class);
    }

    public void dispose() {
        this.zdroje.dispose();
    }
}
