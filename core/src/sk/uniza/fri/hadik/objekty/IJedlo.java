package sk.uniza.fri.hadik.objekty;

public interface IJedlo {
    public int getZvacsenieChvostaHadika();
    public float getZrychlenieHadika();
    public float getDobaZmiznutia();
    public void odcitajDobu(float deltaTime);
}
