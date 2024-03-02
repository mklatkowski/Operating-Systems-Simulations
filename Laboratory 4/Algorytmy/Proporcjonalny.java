package Algorytmy;

import Main.LRU;
import Procesy.Proces;

import java.util.ArrayList;

public class Proporcjonalny implements Algorytm{

    public void alg(ArrayList<Proces> list, int liczbaRamek, int maxOdwolania){
        int sum=0;

        for(Proces x: list){
            sum+=x.getPamiec().size();
        }
        for(Proces x: list){
            double liczba = (liczbaRamek*1.00/sum)*x.getPamiec().size()*100/100.00;
            int liczba1 = ((liczbaRamek*x.getPamiec().size())/sum);

            int ret;

            if(Math.abs(liczba-liczba1)<0.5){
                ret = liczba1;
            }
            else{
                ret = liczba1+1;
            }
            if(ret==0){
                ret = 1;
            }

            x.setLiczbaRamek(ret);
        }
        LRU lru = new LRU();
        System.out.println("Proporcjonalny: " +lru.LRU(list));
        System.out.println(" ");

        for(Proces x: list){
            System.out.println("Liczba stron: " +x.getPamiec().size() + " Liczba ramek: " +x.getLiczbaRamek());
        }
        System.out.println(" ");
    }
}
