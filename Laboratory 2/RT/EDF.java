package RT;

import Proces.ProcesRT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EDF implements RT{

    public int obecnyCzas(ArrayList<ProcesRT> ciag, int pozycjaPoczatkowa){
        if(ciag.size()==0){
            return pozycjaPoczatkowa;
        }
        else {
            Comparator2 comp = new Comparator2();
            Collections.sort(ciag, comp);

            return ciag.get(ciag.size() - 1).getCzas();
        }
    }

    public int calkowityCzas(ArrayList<ProcesRT> ciag, int pozycjaPoczatkowa, int max){
        if(ciag.size()==0){
            return 0;
        }
        else {
            Comparator2 comp = new Comparator2();

            Collections.sort(ciag, comp);

            int czasRT = Math.abs(pozycjaPoczatkowa - ciag.get(0).getCzas());
            int obecnaPozycja = ciag.get(0).getCzas();

            while (ciag.size() != 1) {
                czasRT += Math.abs(obecnaPozycja - ciag.get(1).getCzas());
                obecnaPozycja = ciag.get(1).getCzas();
                ciag.remove(0);
            }

            return czasRT;
        }
    }

    static class Comparator2 implements Comparator<ProcesRT> {
        public int compare(ProcesRT o1, ProcesRT o2) {
            return Integer.compare(o1.getEtykieta(), o2.getEtykieta());
        }
    }
}
