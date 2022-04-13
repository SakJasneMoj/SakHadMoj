package sk.uniza.fri.hadik;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import sk.uniza.fri.hadik.objekty.*;
import sk.uniza.fri.hadik.zdroje.ManazerZdrojov;

public class HraHadik extends ApplicationAdapter {

	public static final int SIRKA = 20; //640;
	public static final int VYSKA = 15; //480;
	public static final int VELKOST_POLICKA = 1; //32;

	private Camera kamera;
	private Viewport viewport;
	private SpriteBatch batch;
	private ManazerZdrojov manazerZdrojov;

	private Hadik hadik;
	private Array<HernyObjekt> herneObjekty;
	private float hodiny;

	private Texture background;

	@Override
	public void create() {

		this.kamera = new OrthographicCamera();
		this.viewport = new FitViewport(HraHadik.SIRKA, HraHadik.VYSKA, this.kamera);

		this.batch = new SpriteBatch();
		this.manazerZdrojov = new ManazerZdrojov();

		this.hadik = new Hadik(this.manazerZdrojov, HraHadik.SIRKA / 2, HraHadik.VYSKA / 2);
		this.herneObjekty = new Array<>();


		this.background = new Texture("background.jpg");


		// TODO: Pridajte steny na pozicie [2,2], [3,2], [4,2], [5,2] a

		// TODO: Vygenerujte tri jedla na pozicie, kde este nie je jedlo, stena alebo hadik



		this.vygenerujStenu();
		this.vygenerujStenu();
		this.vygenerujStenu();
		this.vygenerujStenu();
		this.vygenerujStenu();


	}

	private void vygenerujStenu() {
		GridPoint2 pozicia = najdiVolnuPoziciu();
		HernyObjekt stena = new Stena(this.manazerZdrojov, pozicia.x, pozicia.y);
		this.herneObjekty.add(stena);
	}
	private void vygenerujJedlo() {
		// pridam jedlo do hernych objektov
		GridPoint2 pozicia = najdiVolnuPoziciu();
		HernyObjekt jedlo = null;
		// switch na nahodne vygenerovane jedlo
		switch (MathUtils.random(4)) {
			case 0:
				jedlo = new Jablko(this.manazerZdrojov, pozicia.x, pozicia.y);
				break;
			case 1:
				jedlo = new Lenochod(this.manazerZdrojov, pozicia.x, pozicia.y);
				break;
			case 2:
				jedlo = new Zaba(this.manazerZdrojov, pozicia.x, pozicia.y);
				break;
			case 3:
				jedlo = new Sliepka(this.manazerZdrojov, pozicia.x, pozicia.y);
				break;
			case 4:
				jedlo = new Mrkva(this.manazerZdrojov, pozicia.x, pozicia.y);
				break;
		}
		this.herneObjekty.add(jedlo);
	}

	private GridPoint2 najdiVolnuPoziciu() {
		int poziciaX = MathUtils.random(0, HraHadik.SIRKA - 1);
		int poziciaY = MathUtils.random(0, HraHadik.VYSKA - 1);
		return new GridPoint2(poziciaX, poziciaY);
	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(Color.GREEN);


		this.batch.begin();

		this.batch.draw(this.background, 0, 0, 20, 15);


		this.hodiny += delta;

		if (this.hodiny >= 5) {
			this.vygenerujJedlo();
			this.vygenerujStenu();
			this.hodiny = 0;
		}


		for (HernyObjekt hernyObjekt : this.herneObjekty) {
			if (hernyObjekt instanceof IJedlo) {
				((IJedlo) hernyObjekt).odcitajDobu(delta);
			}

			if (hernyObjekt instanceof IPohyblivy) {

				if (hernyObjekt instanceof Sliepka && !((Sliepka) hernyObjekt).isDospela()) {
					((Sliepka) hernyObjekt).rast(delta);
				}
				((PohybliveJedlo)hernyObjekt).posun(delta);

			}
			hernyObjekt.vykresli(this.batch);

		}
		this.hadik.vykresli(this.batch);
		this.batch.end();

		if (this.hadik.isMrtvy()) {
			return;
		}


		hadik.posun(delta);
		// TODO: Implementujte hernu logiku - kolizie hadika s hernymi objektmi, pohyb pohyblivych objektov

		for (int i = 0; i < this.herneObjekty.size; i++) {
			if ((this.herneObjekty.get(i) instanceof IJedlo) && ((IJedlo) this.herneObjekty.get(i)).getDobaZmiznutia() <= 0) {
				this.herneObjekty.removeIndex(i);
			} else {
				HernyObjekt hernyObjekt = this.herneObjekty.get(i);
				if (this.hadik.kolizia(hernyObjekt.getPoziciaX(), hernyObjekt.getPoziciaY())) {
					if (this.hadik.zjedz(hernyObjekt)) {
						this.herneObjekty.removeIndex(i);
						this.vygenerujJedlo();
					} else {
						return;
					}
				}

			}
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			this.hadik.zmenSmer(Smer.HORE);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			this.hadik.zmenSmer(Smer.DOLE);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			this.hadik.zmenSmer(Smer.VPRAVO);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			this.hadik.zmenSmer(Smer.VLAVO);
		}
	}

	@Override
	public void resize(int sirka, int vyska) {
		this.viewport.update(sirka, vyska, true);
		this.batch.setProjectionMatrix(this.kamera.combined);
	}

	@Override
	public void dispose() {
		this.batch.dispose();
		this.manazerZdrojov.dispose();
	}
}
