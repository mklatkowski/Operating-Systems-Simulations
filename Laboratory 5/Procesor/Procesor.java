package Procesor;

import java.util.ArrayList;
import java.util.Random;

public class Procesor {

    private ArrayList<Zadanie> ciagZadan;
    private int obciazenie;
    private ArrayList<Zadanie> aktywneZadania;
    private int index;

    public Procesor(int maxMoment, int maxDlugoscZadania, int maxRoznicaMiedzyProcesami, int index){
        int czas=-1;

        int maxZapelnienie = 10;
        ciagZadan = new ArrayList<>();
        while(czas<maxMoment){

            Random r = new Random();
            int rr = r.nextInt(1, maxDlugoscZadania); // czas trwania;

            Random p = new Random();
            int pp = p.nextInt(1, maxRoznicaMiedzyProcesami); //

            czas+=pp;

            Random q = new Random();
            int qq = q.nextInt(1, maxZapelnienie);

            ciagZadan.add(new Zadanie(qq, czas, rr));
        }

        obciazenie = 0;
        aktywneZadania=new ArrayList<>();
        this.index = index;
    }

    public Procesor(){
        ciagZadan = new ArrayList<>();
        obciazenie = 0;
        aktywneZadania=new ArrayList<>();
    }

    public ArrayList<Zadanie> getCiagZadan() {
        return ciagZadan;
    }

    public void setCiagZadan(ArrayList<Zadanie> ciagZadan) {
        this.ciagZadan = ciagZadan;
    }

    public int getObciazenie() {
        return obciazenie;
    }

    public void setObciazenie(int obc) {
        this.obciazenie = this.obciazenie+obc;
    }

    public void dodajDoAktywnych(Zadanie z){
        aktywneZadania.add(z);
    }
    public void usunZAktywnych(Zadanie z){
        aktywneZadania.remove(z);
    }

    public ArrayList<Zadanie> getAktywneZadania() {
        return aktywneZadania;
    }

    public void setAktywneZadania(ArrayList<Zadanie> aktywneZadania) {
        this.aktywneZadania = aktywneZadania;
    }

    public void obnizObciazenie(int obc){
        this.obciazenie = this.obciazenie-obc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Procesor{" +
                ", obciazenie=" + obciazenie +
                ", indeks=" + index+
                '}';
    }
}
