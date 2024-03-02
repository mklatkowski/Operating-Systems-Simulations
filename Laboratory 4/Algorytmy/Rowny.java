package Algorytmy;

import Main.LRU;
import Procesy.Proces;

import java.util.ArrayList;

public class Rowny implements Algorytm{

    public void alg(ArrayList<Proces> list, int liczbaRamek, int maxOdwolania){
        int ramki = liczbaRamek/list.size();
        for(Proces x: list){
            x.setLiczbaRamek(ramki);
        }
        LRU lru = new LRU();
        System.out.println("Równy: " +lru.LRU(list));
    }
}
