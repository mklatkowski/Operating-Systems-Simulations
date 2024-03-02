package Main;

import Algorytmy.Algorytm;
import Algorytmy.Algorytm1;
import Algorytmy.Algorytm2;
import Algorytmy.Algorytm3;
import Procesor.*;

import java.util.ArrayList;

public class Main {

    public static void realizacja(ArrayList<Procesor> list, int maxRoznicaMiedzyProcesami, int maxMoment, int liczbaLosowan, int max, int min) {

        Algorytm alg1 = new Algorytm1();
        Algorytm alg2 = new Algorytm2();
        Algorytm alg3 = new Algorytm3();

        alg1.alg(copy(list), maxRoznicaMiedzyProcesami, maxMoment, liczbaLosowan, max, min);
        alg2.alg(copy(list), maxRoznicaMiedzyProcesami, maxMoment, liczbaLosowan, max, min);
        alg3.alg(copy(list), maxRoznicaMiedzyProcesami, maxMoment, liczbaLosowan, max, min);
    }

    public static ArrayList<Procesor> generujProcesory(int N, int maxMoment, int maxDlugoscTrwaniaZadania, int maxRoznicaMiedzyProcesami) {
        ArrayList<Procesor> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new Procesor(maxMoment, maxDlugoscTrwaniaZadania, maxRoznicaMiedzyProcesami, i));
        }
        return list;
    }

    public static void main(String[] args) {

        //dane do algorytmow
        int N = 40;
        int liczbaLosowan = 10;
        int max = 80;
        int min = 20;


        //dane do procesorow
        int maxMoment = 1000;
        int maxDlugoscTrwaniaZadania = 40;
        int maxRoznicaMiedzyProcesami = 2;

        ArrayList<Procesor> procesory = generujProcesory(N, maxMoment, maxDlugoscTrwaniaZadania, maxRoznicaMiedzyProcesami);

        realizacja(procesory, maxRoznicaMiedzyProcesami, maxMoment, liczbaLosowan, max, min);

    }

    public static ArrayList<Procesor> copy(ArrayList<Procesor> list){
        ArrayList<Procesor> ret = new ArrayList<>();
        for(Procesor x: list){
            Procesor temp = new Procesor();
            ArrayList<Zadanie> zadania = new ArrayList<>();
            for(Zadanie y: x.getCiagZadan()){
                zadania.add(new Zadanie(y.getZapelnienie(), y.getMomentDolaczenia(), y.getCzasTrwania()));
            }
            temp.setCiagZadan(zadania);
            temp.setIndex(x.getIndex());
            ret.add(temp);
        }
        return ret;
    }
}
