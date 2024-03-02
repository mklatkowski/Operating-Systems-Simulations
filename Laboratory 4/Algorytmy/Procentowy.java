package Algorytmy;

import Main.LRU;
import Procesy.Proces;
import Procesy.Strona;

import java.util.ArrayList;

import static Main.Main.generujGlobalnyCiag;

public class Procentowy implements Algorytm{

    public void alg(ArrayList<Proces> list, int liczbaRamek, int maxOdwolania){
        int podzial = 50;
        LRU lru = new LRU();
        for(Proces x: list){
            int zbiorRoboczy = 1;
            for(int i=0; i<x.getCiagOdwolan().size(); i++){
                if(x.getCiagOdwolan().get(i).equals(x.getCiagOdwolan().get(i+1))){
                    break;
                }
                else{
                    zbiorRoboczy++;
                }
            }
            x.setLiczbaRamek(zbiorRoboczy);
        }
        ArrayList<Strona> globalnyCiagOdwolan = new ArrayList<>();
        generujGlobalnyCiag(list, globalnyCiagOdwolan);


        int liczbaBledow = lru.LRUProcentowy(list, globalnyCiagOdwolan);
        int tempLiczbaRamek =0;
        int sredniaLiczbaOdwolan =0;
        for(Proces x: list){
            tempLiczbaRamek+=x.getLiczbaRamek();
            sredniaLiczbaOdwolan+=x.getCiagOdwolan().size();
        }
        liczbaBledow = liczbaBledow - (maxOdwolania/podzial)*tempLiczbaRamek;
        System.out.println("Procentowy: " +liczbaBledow);
    }
}
