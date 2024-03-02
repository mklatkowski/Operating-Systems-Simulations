package Algorytmy;

import Main.LRU;
import Procesy.Proces;

import java.util.ArrayList;

public class Sferyczny implements Algorytm{

    public void alg(ArrayList<Proces> list, int liczbaRamek, int maxOdwolania){
        LRU lru = new LRU();

        final int podzial = 50;
        int indeks = 0;
        boolean b = false;

//        for(Proces x: list){
//            int zbiorRoboczy = 1;
//            for(int i=0; i<x.getCiagOdwolan().size(); i++){
//                if(x.getCiagOdwolan().get(i).equals(x.getCiagOdwolan().get(i+1))){
//                    break;
//                }
//                else{
//                    zbiorRoboczy++;
//                }
//            }
//            x.setLiczbaRamek(zbiorRoboczy);
//        }
//        System.out.println((int)(0.7*lru.LRU(list)-(2800/50)*list.size()));

        while(!b) {
            b = true;
            for (Proces x : list) {
                int zbiorRoboczy = 1;
                if (x.getCiagOdwolan().size() > indeks) {
                    for (int i = 0; i < Math.min(podzial, x.getCiagOdwolan().size() - indeks); i++) {
                        if (x.getCiagOdwolan().get(indeks+i).equals(x.getCiagOdwolan().get(Math.min((indeks+ i+1), x.getCiagOdwolan().size()-1)))) {
                            break;
                        } else {
                            zbiorRoboczy++;
                        }
                    }
                    x.setLiczbaRamek(zbiorRoboczy);
                    lru.LRUdynamiczne(x,  x.getCiagOdwolan().subList(indeks, Math.min(indeks+podzial, x.getCiagOdwolan().size())));
                    b = false;
                }
            }
            indeks+=podzial;
        }
        int liczbaBledow = 0;
        for(Proces x: list){
            liczbaBledow+=x.getLiczbaBledow();
        }
        int tempLiczbaRamek =0;
        int sredniaLiczbaOdwolan =0;
        for(Proces x: list){
            tempLiczbaRamek+=x.getLiczbaRamek();
            sredniaLiczbaOdwolan+=x.getCiagOdwolan().size();
        }
        liczbaBledow = liczbaBledow - (maxOdwolania/podzial)*tempLiczbaRamek;
        System.out.println("Sferyczny: " +liczbaBledow);
    }
}
