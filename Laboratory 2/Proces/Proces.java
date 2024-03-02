package Proces;

import java.util.Objects;

public abstract class Proces implements Cloneable{

    private int czas;
    private int pozycja;
    private static int poz = 1;

    //todo ewentualnie wywalić pozycje, wtedy abstract Proces, ProcesZw, ProcesRT
    //todo do RT dodać etykiety

    public Proces(int czas) {
        this.czas = czas;
        this.pozycja = poz;
        poz++;
    }
    public Proces(int czas, int pozycja){
        this.czas = czas;
        this.pozycja = pozycja;
    }
    public Proces(){
    }

    public int getCzas() {
        return czas;
    }

    public void setCzas(int czas) {
        this.czas = czas;
    }

    public int getPozycja() {
        return pozycja;
    }

    public void setPozycja(int pozycja) {
        this.pozycja = pozycja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proces proces = (Proces) o;
        return pozycja == proces.pozycja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(czas, pozycja);
    }

    @Override
    public String toString() {
        return "Proces{" +
                "dlugoscFazy=" + czas +
                ", pozycja=" + pozycja+
                '}';
    }
    public Proces clone(){
        Proces p = null;
        try{
            p = (Proces) super.clone();
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
//        Proces p = new Proces();
//        p.dlugoscFazy = this.dlugoscFazy.clone();
//        p.pozycja = this.pozycja.clone();
//        p.czyRT = this.czyRT.clone();
        
        return p;
    }
}
