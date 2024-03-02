package Main;

import Algorytmy.*;
import Procesy.Proces;
import Procesy.Strona;


import java.util.ArrayList;


public class Main {

    public static void realizacja(ArrayList<Proces> proces, ArrayList<Strona> globalnyCiagOdwolan, int liczbaRamek, int maxOdwolania){
        Algorytm rowny = new Rowny();
        Algorytm prop = new Proporcjonalny();
        Algorytm sferyczny = new Sferyczny();
        Algorytm procentowy = new Procentowy();
        rowny.alg(proces, liczbaRamek, maxOdwolania);
        prop.alg(proces, liczbaRamek, maxOdwolania);
        sferyczny.alg(proces, liczbaRamek, maxOdwolania);
        procentowy.alg(proces, liczbaRamek, maxOdwolania);
    }

    public static void generujGlobalnyCiag(ArrayList<Proces> procesy, ArrayList<Strona> globalnyCiagOdwolan){
        boolean b=false;
        int temp=0;
        while(!b){
            b=true;
            for(Proces x: procesy){
                if(x.getCiagOdwolan().size()>temp && x.getCiagOdwolan().get(temp)!=null){
                    globalnyCiagOdwolan.add(x.getCiagOdwolan().get(temp));
                    b=false;
                }
            }
            temp++;
        }
    }

    public static void generujProcesy(int max, int maxOdwolania, int liczbaProcesow, ArrayList<Proces> procesy){
        for(int i=0; i<liczbaProcesow; i++){
            procesy.add(new Proces(max, maxOdwolania));
        }
    }

    public static void main(String[] args){

        int max = 500;
        int maxOdwolania = 1000;
        int liczbaProcesow = 40;
        int liczbaRamek = 200;


        ArrayList<Proces> procesy = new ArrayList<>();
        ArrayList<Strona> globalnyCiagOdwolan = new ArrayList<>();

        generujProcesy(max, maxOdwolania, liczbaProcesow, procesy);
        generujGlobalnyCiag(procesy, globalnyCiagOdwolan);

        realizacja(procesy, globalnyCiagOdwolan, liczbaRamek, maxOdwolania);

    }
}
