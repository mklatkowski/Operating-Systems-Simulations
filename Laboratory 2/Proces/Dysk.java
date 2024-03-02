package Proces;

import RT.RT;

import java.util.ArrayList;

public class Dysk {

    private ArrayList<Proces> ciag;
    private int max;
    private int pustyBlokCzas;
    private int pozycjaStartowa;

    private RT rtStrategia;

    public Dysk(ArrayList<Proces> ciag, int max, int pozycjaStartowa, RT rtStrategia) {
        this.ciag = ciag;
        this.max = max;
        this.pozycjaStartowa = pozycjaStartowa;
        this.rtStrategia = rtStrategia;
    }

    public ArrayList<Proces> getCiag() {
        return ciag;
    }

    public void setCiag(ArrayList<Proces> ciag) {
        this.ciag = ciag;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPustyBlokCzas() {
        return pustyBlokCzas;
    }

    public void setPustyBlokCzas(int pustyBlokCzas) {
        this.pustyBlokCzas = pustyBlokCzas;
    }

    public int getPozycjaStartowa() {
        return pozycjaStartowa;
    }

    public int obecnaPozycja(){
        return rtStrategia.obecnyCzas(Add(), pozycjaStartowa);
    }

    public int calkowityCzas(){
        return rtStrategia.calkowityCzas(Add(), pozycjaStartowa, max);
    }

    private ArrayList<ProcesRT> Add(){
        ArrayList<ProcesRT> rt= new ArrayList<>();
        for(Proces x: ciag){
            if(x instanceof ProcesRT){
                rt.add((ProcesRT) x);
            }
        }
        return rt;
    }

    public void setPozycjaStartowa(int pozycjaStartowa) {
        this.pozycjaStartowa = pozycjaStartowa;
    }
}
