package Procesor;

public class Zadanie {

    private int zapelnienie;
    private int momentDolaczenia;
    private int czasTrwania;

    public Zadanie(int zapelnienie, int momentDolaczenia, int czasTrwania) {
        this.zapelnienie = zapelnienie;
        this.momentDolaczenia = momentDolaczenia;
        this.czasTrwania = czasTrwania;
    }

    public int getZapelnienie() {
        return zapelnienie;
    }

    public void setZapelnienie(int zapelnienie) {
        this.zapelnienie = zapelnienie;
    }

    public int getMomentDolaczenia() {
        return momentDolaczenia;
    }

    public void setMomentDolaczenia(int momentDolaczenia) {
        this.momentDolaczenia = momentDolaczenia;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public int getMomentWyjscia(){
        return momentDolaczenia+czasTrwania;
    }

    @Override
    public String toString() {
        return "Zadanie{" +
                "zapelnienie=" + zapelnienie;
//                ", momentDolaczenia=" + momentDolaczenia +
//                ", czasTrwania=" + czasTrwania +
//                '}';
    }
}
