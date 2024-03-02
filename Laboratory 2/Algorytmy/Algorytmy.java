package Algorytmy;

import Proces.Dysk;
import Proces.*;
import RT.EDF;
import RT.EDSCAN;
import RT.RT;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Algorytmy {

    public static Dysk generate() {

        ArrayList<Proces> ciag = new ArrayList<>();

        RT rt = new EDSCAN();
        
//        ciag.add(new ProcesZW(98));
//        ciag.add(new ProcesZW(183));
//        ciag.add(new ProcesZW(40));
//        ciag.add(new ProcesZW(122));
//        ciag.add(new ProcesRT(21, 2));
//        ciag.add(new ProcesZW(10));
//        ciag.add(new ProcesZW(124));
//        ciag.add(new ProcesRT(37, 50));
//        ciag.add(new ProcesRT(53, 80));
//        ciag.add(new ProcesRT(18, 30));
//        ciag.add(new ProcesZW(65));

//        ciag.add(new ProcesZW(82));
//        ciag.add(new ProcesZW(170));
//        ciag.add(new ProcesZW(43));
//        ciag.add(new ProcesZW(140));
//        ciag.add(new ProcesZW(24));
//        ciag.add(new ProcesZW(16));
//        ciag.add(new ProcesZW(190));
//        ciag.add(new ProcesRT(12, 30));
//        ciag.add(new ProcesRT(43, 10));
//        ciag.add(new ProcesRT(82, 20));

        ciag.add(new ProcesZW(82));
        ciag.add(new ProcesZW(170));
        ciag.add(new ProcesZW(43));
        ciag.add(new ProcesZW(140));
        ciag.add(new ProcesZW(24));
        ciag.add(new ProcesZW(16));
        ciag.add(new ProcesZW(190));
        ciag.add(new ProcesRT(49, 30));
        ciag.add(new ProcesRT(43, 10));
        ciag.add(new ProcesRT(45, 20));
        ciag.add(new ProcesRT(60, 5));


        Dysk dysk = new Dysk(ciag, 200, 50,  rt);

        return dysk;
    }

    public static double FCFS(Dysk dysk) {

        int obecnaPozycja = dysk.obecnaPozycja();
        double calkowityCzasOczekiwana = dysk.calkowityCzas();

        dysk.getCiag().removeIf(x -> x instanceof ProcesRT);

        calkowityCzasOczekiwana += Math.abs(obecnaPozycja - dysk.getCiag().get(0).getCzas());

        while (dysk.getCiag().size() != 1) {
            calkowityCzasOczekiwana += Math.abs(dysk.getCiag().get(0).getCzas() - dysk.getCiag().get(1).getCzas());
            dysk.getCiag().remove(0);
        }
        return calkowityCzasOczekiwana;
    }

    public static double SSTF(Dysk dysk) {

        int obecnaPozycja = dysk.obecnaPozycja();
        double calkowityCzasOczekiwana = dysk.calkowityCzas();

        dysk.getCiag().removeIf(x -> x instanceof ProcesRT);

        Comparator1 comp = new Comparator1();

        ArrayList<Proces> temp = new ArrayList<>();

        for (int i = 0; i < dysk.getCiag().size(); i++) {
            int dl = dysk.getCiag().get(i).getCzas();
            int poz = dysk.getCiag().get(i).getPozycja();
            Proces p = new ProcesZW(dl, poz);
            temp.add(p);
        }


        for (Proces x : temp) {
            x.setCzas(Math.abs(x.getCzas() - dysk.getPozycjaStartowa()));
        }

        Collections.sort(temp, comp);
        calkowityCzasOczekiwana += temp.get(0).getCzas();

        for (Proces x : dysk.getCiag()) {
            if (x.equals(temp.get(0))) {
                obecnaPozycja = x.getCzas();
                break;
            }
        }


        while (dysk.getCiag().size() != 1) {
            for (int i = 0; i < dysk.getCiag().size(); i++) {
                if (dysk.getCiag().get(i).equals(temp.get(0))) {
                    dysk.getCiag().remove(i);
                    break;
                }
            }

            temp.clear();

            for (int i = 0; i < dysk.getCiag().size(); i++) {

                int dl = dysk.getCiag().get(i).getCzas();
                int poz = dysk.getCiag().get(i).getPozycja();
                Proces p = new ProcesZW(dl, poz);
                temp.add(p);

            }

            for (Proces x : temp) {
                x.setCzas(Math.abs(x.getCzas() - obecnaPozycja));
            }
            Collections.sort(temp, comp);

            calkowityCzasOczekiwana = calkowityCzasOczekiwana + temp.get(0).getCzas();
            for (Proces x : dysk.getCiag()) {
                if (temp.get(0).equals(x)) {
                    obecnaPozycja = x.getCzas();
                    break;
                }
            }
        }

        return calkowityCzasOczekiwana;
    }

    public static double SCAN(Dysk dysk) {

        int obecnaPozycja = dysk.obecnaPozycja();
        double calkowityCzasOczekiwana = dysk.calkowityCzas();

        dysk.getCiag().removeIf(x -> x instanceof ProcesRT);

        ArrayList<Proces> temp = dysk.getCiag();

        Comparator1 comp = new Comparator1();
        Collections.sort(temp, comp);


        calkowityCzasOczekiwana += dysk.getMax() - obecnaPozycja;

        temp.removeIf(x -> x.getCzas() >= obecnaPozycja);

        if (!temp.isEmpty()) {
            calkowityCzasOczekiwana += dysk.getMax()-temp.get(0).getCzas();
        }

        return calkowityCzasOczekiwana;
    }

    public static double CSCAN(Dysk dysk) {

        int obecnaPozycja = dysk.obecnaPozycja();
        double calkowityCzasOczekiwana = dysk.calkowityCzas();

        dysk.getCiag().removeIf(x -> x instanceof ProcesRT);

        ArrayList<Proces> temp = dysk.getCiag();

        Comparator1 comp = new Comparator1();
        Collections.sort(temp, comp);


        calkowityCzasOczekiwana += dysk.getMax() - obecnaPozycja;
        temp.removeIf(x -> x.getCzas() >= obecnaPozycja);
        if (!temp.isEmpty()) {
            calkowityCzasOczekiwana += temp.get(temp.size() - 1).getCzas();
            calkowityCzasOczekiwana+=200;
        }

        return calkowityCzasOczekiwana;
    }

    public static void main(String[] args) {
        Dysk dysk1 = generate();
        Dysk dysk2 = generate();
        Dysk dysk3 = generate();
        Dysk dysk4 = generate();

        System.out.println("FCFS: " + FCFS(dysk1));
        System.out.println("SSTF:" + SSTF(dysk2));
        System.out.println("SCAN: " + SCAN(dysk3));
        System.out.println("C-SCAN: " + CSCAN(dysk4));
    }

    static class Comparator1 implements Comparator<Proces> {
        public int compare(Proces o1, Proces o2) {
            return Integer.compare(o1.getCzas(), o2.getCzas());
        }
    }
}

