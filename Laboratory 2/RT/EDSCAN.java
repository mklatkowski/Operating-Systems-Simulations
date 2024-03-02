package RT;

import Proces.Proces;
import Proces.ProcesRT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EDSCAN implements RT{

    public int obecnyCzas(ArrayList<ProcesRT> ciag, int pozycjaPoczatkowa){
        if(ciag.size()==0){
            return pozycjaPoczatkowa;
        }
        else {
            Comparator1 comp1 = new Comparator1();
            Comparator2 comp = new Comparator2();
            Collections.sort(ciag, comp);
            if(ciag.get(0).getCzas()<pozycjaPoczatkowa){
                Collections.sort(ciag,comp1);
                return ciag.get(ciag.size()-1).getCzas();
            }
            else{
                Collections.sort(ciag,comp1);
                return ciag.get(0).getCzas();
            }
        }
    }

    public int calkowityCzas(ArrayList<ProcesRT> ciag, int pozycjaPoczatkowa, int max){
        if(ciag.size()==0){
            return 0;
        }
        else {
            Comparator1 comp1 = new Comparator1();
            Comparator2 comp = new Comparator2();
            Collections.sort(ciag, comp);
            if(ciag.get(0).getCzas()<pozycjaPoczatkowa){
                Collections.sort(ciag,comp1);
                return 2*pozycjaPoczatkowa+ciag.get(ciag.size()-1).getCzas();
            }
            else{
                Collections.sort(ciag,comp1);
                return 2*(max-pozycjaPoczatkowa) + pozycjaPoczatkowa - ciag.get(0).getCzas();
            }
        }
    }

    static class Comparator1 implements Comparator<Proces> {
        public int compare(Proces o1, Proces o2) {
            return Integer.compare(o1.getCzas(), o2.getCzas());
        }
    }

    static class Comparator2 implements Comparator<ProcesRT> {
        public int compare(ProcesRT o1, ProcesRT o2) {
            return Integer.compare(o1.getEtykieta(), o2.getEtykieta());
        }
    }
}

