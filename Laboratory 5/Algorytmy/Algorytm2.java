package Algorytmy;

import Procesor.Procesor;
import Procesor.Zadanie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Algorytm2 implements Algorytm {

    public void alg(ArrayList<Procesor> list, int maxRoznicaMiedzyProcesami, int maxMoment, int liczbaLosowan, int max, int min) {

        HashMap<Procesor, Integer> odchylenie = new HashMap<>();
        HashMap<Procesor, Integer> odchylenie2 = new HashMap<>();
        for(Procesor p :list){
            odchylenie.put(p, 0);
            odchylenie2.put(p, p.getCiagZadan().size());
        }
        int liczbaPorownan = 0;
        int liczbaMigracji = 0;
        int obecnyCzas = 0;

        ArrayList<Double> srednie = new ArrayList<>();
        int suma =0;

        while (obecnyCzas < maxMoment + maxRoznicaMiedzyProcesami) {
            for (Procesor x : list) {
                if (!x.getCiagZadan().isEmpty()) {
                    int z = x.getCiagZadan().get(0).getMomentDolaczenia();
                    int obc = x.getCiagZadan().get(0).getZapelnienie();
                    if (z == obecnyCzas) {
                        if (x.getObciazenie() < max) {
                            x.setObciazenie(obc);
                            x.dodajDoAktywnych(x.getCiagZadan().get(0));
                        } else {
                            boolean c = false;
                            int counter = 0;
                            while (!c) {
                                Random r = new Random();
                                int rr = r.nextInt(list.size());
                                liczbaPorownan++;
                                if (list.get(rr).getObciazenie() < max) {
                                    liczbaMigracji++;
                                    list.get(rr).setObciazenie(obc);
                                    list.get(rr).dodajDoAktywnych(x.getCiagZadan().get(0));
                                    c = true;
                                }
                                if(!c && counter==liczbaLosowan-1){
                                    break;
                                }
                                counter++;
                            }
                        }
                        x.getCiagZadan().remove(0);
                    }
                }
                suma+=x.getObciazenie();
                odchylenie.put(x, odchylenie.get(x)+x.getObciazenie());
            }

            for (Procesor proces : list) {
                for (Iterator<Zadanie> it = proces.getAktywneZadania().iterator(); it.hasNext(); ) {
                    Zadanie next = it.next();
                    if (next.getMomentWyjscia() == obecnyCzas) {
                        proces.obnizObciazenie(next.getZapelnienie());
                        it.remove();
                    }
                }
            }
            if((obecnyCzas+1)%50==0){
                srednie.add(suma*1.00/(50*list.size()));
                suma = 0;
            }
            obecnyCzas++;
        }
        double Sum = 0;
        for(Double zzz: srednie){
            Sum+=zzz;
        }
        double odchylenieResult = 0;
        double sredniaW = Sum /srednie.size();
        for(Procesor p: list){
            odchylenieResult+=((odchylenie.get(p)*1.00/odchylenie2.get(p))-sredniaW)*(odchylenie.get(p)*1.00/odchylenie2.get(p)-sredniaW);
        }
        double odchylenieR = Math.sqrt(odchylenieResult/(list.size()-1));

        System.out.println("liczba porownan 2: " + liczbaPorownan);
        System.out.println("liczba migracji 2: " + liczbaMigracji);
        System.out.println("Srednia: ");
        System.out.printf("%3.2f%n", sredniaW);
        System.out.print("Odchylenie: ");
        System.out.printf("%3.2f%n", odchylenieR);
        System.out.println();
    }

}
