package Proces;

public class Proces {

    private int numerProcesu;
    private int dlFazyProcesora;
    private int momZgloszenia;
    private int czasOczekiwania;

    public Proces(int numerProcesu, int dlFazyProcesora, int momZgloszenia) {
        this.numerProcesu = numerProcesu;
        this.dlFazyProcesora = dlFazyProcesora;
        this.momZgloszenia = momZgloszenia;
        this.czasOczekiwania = 0;
    }

    public int getNumerProcesu() {
        return numerProcesu;
    }

    public void setNumerProcesu(int numerProcesu) {
        this.numerProcesu = numerProcesu;
    }

    public int getDlFazyProcesora() {
        return dlFazyProcesora;
    }

    public void setDlFazyProcesora(int dlFazyProcesora) {
        this.dlFazyProcesora = dlFazyProcesora;
    }

    public int getMomZgloszenia() {
        return momZgloszenia;
    }

    public void setMomZgloszenia(int momZgloszenia) {
        this.momZgloszenia = momZgloszenia;
    }

    public int getCzasOczekiwania() {
        return czasOczekiwania;
    }

    public void setCzasOczekiwania(int czasOczekiwania) {
        this.czasOczekiwania = czasOczekiwania;
    }

    public String toString(){
        return "Numer procesu: "  + numerProcesu + ", długość fazy procesora: " +dlFazyProcesora + ", Moment zgłoszenia: " + momZgloszenia + ", Czas oczekiwania: " + czasOczekiwania;
    }
}
