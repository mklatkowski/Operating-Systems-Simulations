package Algorytmy;

import Strona.CiagOdwolan;
import Strona.Strona;
import Strona.PamiecLogiczna;
import Strona.Queue;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Algorytmy {

    private int liczbaRamek;
    private Strona[] ramki;
    private PamiecLogiczna pamiec;


    public Algorytmy(int liczbaRamek){
        this.liczbaRamek = liczbaRamek;
        ramki = new Strona[liczbaRamek];
    }

    public CiagOdwolan generate(int liczbaStron, int liczbaOdwolan){
        CiagOdwolan ciag = new CiagOdwolan(liczbaOdwolan, new PamiecLogiczna(liczbaStron));
//        ciag.getCiag().add(new Strona(1));
//        ciag.getCiag().add(new Strona(2));
//        ciag.getCiag().add(new Strona(3));
//        ciag.getCiag().add(new Strona(4));
//        ciag.getCiag().add(new Strona(1));
//        ciag.getCiag().add(new Strona(2));
//        ciag.getCiag().add(new Strona(5));
//        ciag.getCiag().add(new Strona(1));
//        ciag.getCiag().add(new Strona(2));
//        ciag.getCiag().add(new Strona(3));
//        ciag.getCiag().add(new Strona(4));
//        ciag.getCiag().add(new Strona(5));
//        ciag.getCiag().add(new Strona(1));

        return ciag;

    }

    public int OPT(CiagOdwolan ciag){
        for(int i=0 ;i<ramki.length; i++){
            ramki[i] = null;
        }
        int liczbaBledow = 0;
        int liczbaWykonanychOdwolan = 0;

        int inicjalizacjaRamek =0;
        while(ramki[ramki.length-1]==null && liczbaWykonanychOdwolan<ciag.getLiczbaOdwolan()){
            if(ramki[0] == null){
                ramki[0] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                liczbaWykonanychOdwolan++;
                inicjalizacjaRamek++;
                liczbaBledow++;
            }
            else{
                for(int i=inicjalizacjaRamek; i>0; i--){
                    if(ramki[i-1].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        liczbaWykonanychOdwolan++;
                        break;
                    }
                    if(i==1 && !ramki[0].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        ramki[inicjalizacjaRamek] = ciag.getCiag().get(liczbaWykonanychOdwolan);

                        liczbaWykonanychOdwolan++;
                        inicjalizacjaRamek++;
                        liczbaBledow++;
                        break;
                    }
                }
            }
        }
        while(liczbaWykonanychOdwolan<ciag.getCiag().size()){
            boolean czyBlad = true;
            for(int i=0; i<ramki.length; i++){
                if(ciag.getCiag().get(liczbaWykonanychOdwolan).equals(ramki[i])){
                    czyBlad=false;
                    break;
                }
            }
            if(czyBlad){
                ArrayList<Strona> temp = new ArrayList<>();
                int tempStan = liczbaWykonanychOdwolan;
                temp.addAll(Arrays.asList(ramki));
                while(temp.size()!=1 && tempStan<ciag.getCiag().size()){
                    for(Strona x: temp){
                        if(ciag.getCiag().get(tempStan).equals(x)){
                            temp.remove(x);
                            break;
                        }
                    }
                    tempStan++;
                }
                if(temp.size()!=1){
                    for(int i=1; i<temp.size(); i++){
                        temp.remove(i);
                    }
                }
                for(int i=0; i<ramki.length; i++){
                    if(ramki[i].equals(temp.get(0))){
                        ramki[i] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                    }
                }
                liczbaBledow++;
            }
            liczbaWykonanychOdwolan++;
        }
        return liczbaBledow;
    }

    public int LRU(CiagOdwolan ciag){
        for(int i=0 ;i<ramki.length; i++){
            ramki[i] = null;
        }
        int liczbaBledow = 0;
        int liczbaWykonanychOdwolan = 0;

        int inicjalizacjaRamek =0;
        while(ramki[ramki.length-1]==null && liczbaWykonanychOdwolan<ciag.getLiczbaOdwolan()){
            if(ramki[0] == null){
                ramki[0] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                liczbaWykonanychOdwolan++;
                inicjalizacjaRamek++;
                liczbaBledow++;
            }
            else{
                for(int i=inicjalizacjaRamek; i>0; i--){
                    if(ramki[i-1].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        liczbaWykonanychOdwolan++;
                        break;
                    }
                    if(i==1 && !ramki[0].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        ramki[inicjalizacjaRamek] = ciag.getCiag().get(liczbaWykonanychOdwolan);

                        liczbaWykonanychOdwolan++;
                        inicjalizacjaRamek++;
                        liczbaBledow++;
                    }
                }
            }
        }
        while(liczbaWykonanychOdwolan<ciag.getCiag().size()){
            boolean czyBlad = true;
            for(int i=0; i<ramki.length; i++){
                if(ciag.getCiag().get(liczbaWykonanychOdwolan).equals(ramki[i])){
                    czyBlad=false;
                    break;
                }
            }
            if(czyBlad){
                ArrayList<Strona> temp = new ArrayList<>();
                int tempStan = liczbaWykonanychOdwolan;
                temp.addAll(Arrays.asList(ramki));
                while(temp.size()!=1 && tempStan>0){
                    for(Strona x: temp){
                        if(ciag.getCiag().get(tempStan).equals(x)){
                            temp.remove(x);
                            break;
                        }
                    }
                    tempStan--;
                }
                if(temp.size()!=1){
                    for(int i=1; i<temp.size(); i++){
                        temp.remove(i);
                    }
                }
                for(int i=0; i<ramki.length; i++){
                    if(ramki[i].equals(temp.get(0))){
                        ramki[i] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                    }
                }
                liczbaBledow++;
            }
            liczbaWykonanychOdwolan++;
        }
        return liczbaBledow;
    }

    public int LRUAPX(CiagOdwolan ciag){
        Arrays.fill(ramki, null);

        int liczbaBledow = 0;
        int liczbaWykonanychOdwolan = 0;

        int inicjalizacjaRamek = 0;

        Queue<Strona> q = new Queue<Strona>();


        while(ramki[ramki.length-1]==null && liczbaWykonanychOdwolan<ciag.getCiag().size()){
            if(ramki[0] == null){
                ramki[0] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                q.enqueue(ciag.getCiag().get(liczbaWykonanychOdwolan));

                liczbaWykonanychOdwolan++;
                inicjalizacjaRamek++;
                liczbaBledow++;
            }
            else{
                for(int i=inicjalizacjaRamek; i>0; i--){
                    if(ramki[i-1].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        liczbaWykonanychOdwolan++;
                        break;
                    }
                    if(i==1 && !ramki[0].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        ramki[inicjalizacjaRamek] = ciag.getCiag().get(liczbaWykonanychOdwolan);

                        q.enqueue(ciag.getCiag().get(liczbaWykonanychOdwolan));

                        inicjalizacjaRamek++;
                        liczbaWykonanychOdwolan++;
                        liczbaBledow++;
                    }
                }
            }
        }
        while(liczbaWykonanychOdwolan<ciag.getCiag().size()){
            boolean czyBlad = true;
            for(int i=0; i<ramki.length; i++){
                if(ciag.getCiag().get(liczbaWykonanychOdwolan).equals(ramki[i])){
                    czyBlad=false;
                    break;
                }
            }
            if(czyBlad) {
                while(true){
                    if(q.get(0).getBitOdwolania()==0){
                        Strona temp1 = q.dequeue();
                        q.enqueue(ciag.getCiag().get(liczbaWykonanychOdwolan));
                        for(int i=0; i<ramki.length; i++){
                            if(ramki[i].equals(temp1)){
                                ramki[i] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                                break;
                            }
                        }
                        break;
                    }
                    else{
                        q.get(0).setBitOdwolania(0);
                        q.enqueue(q.dequeue());
                    }
                }
                liczbaBledow++;
            }
            else{
                for(int i=0; i<ramki.length; i++){
                    if(q.get(i).equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        q.get(i).setBitOdwolania(1-q.get(i).getBitOdwolania());
                        break;
                    }
                }
            }
            liczbaWykonanychOdwolan++;
        }
        return liczbaBledow;
    }

    public int FIFO(CiagOdwolan ciag){
        Arrays.fill(ramki, null);

        int liczbaBledow = 0;
        int liczbaWykonanychOdwolan = 0;

        int inicjalizacjaRamek = 0;

        while(ramki[ramki.length-1]==null && liczbaWykonanychOdwolan<ciag.getLiczbaOdwolan()){
            if(ramki[0] == null){
                ramki[0] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                liczbaWykonanychOdwolan++;
                inicjalizacjaRamek++;
                liczbaBledow++;
            }
            else{
                for(int i=inicjalizacjaRamek; i>0; i--){
                    if(ramki[i-1].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        liczbaWykonanychOdwolan++;
                        break;
                    }
                    if(i==1 && !ramki[0].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        ramki[inicjalizacjaRamek] = ciag.getCiag().get(liczbaWykonanychOdwolan);

                        liczbaWykonanychOdwolan++;
                        inicjalizacjaRamek++;
                        liczbaBledow++;
                    }
                }
            }
        }

        inicjalizacjaRamek = 0;
        while(liczbaWykonanychOdwolan<ciag.getCiag().size()){
            boolean czyBlad = true;
            for(int i=0; i<ramki.length; i++){
                if(ciag.getCiag().get(liczbaWykonanychOdwolan).equals(ramki[i])){
                    czyBlad=false;
                    break;
                }
            }
            if(czyBlad){
                ramki[inicjalizacjaRamek] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                if(inicjalizacjaRamek==ramki.length-1){
                    inicjalizacjaRamek = 0;
                }
                else{
                    inicjalizacjaRamek++;
                }
                liczbaBledow++;
            }
            liczbaWykonanychOdwolan++;
        }

        return liczbaBledow;
    }

    public int RAND(CiagOdwolan ciag){
        Arrays.fill(ramki, null);

        int liczbaBledow = 0;
        int liczbaWykonanychOdwolan = 0;

        int inicjalizacjaRamek =0;

        while(ramki[ramki.length-1]==null && liczbaWykonanychOdwolan<ciag.getLiczbaOdwolan()){
            if(ramki[0] == null){
                ramki[0] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                liczbaWykonanychOdwolan++;
                inicjalizacjaRamek++;
                liczbaBledow++;
            }
            else{
                for(int i=inicjalizacjaRamek; i>0; i--){
                    if(ramki[i-1].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        liczbaWykonanychOdwolan++;
                        break;
                    }
                    if(i==1 && !ramki[0].equals(ciag.getCiag().get(liczbaWykonanychOdwolan))){
                        ramki[inicjalizacjaRamek] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                        inicjalizacjaRamek++;
                        liczbaBledow++;
                    }
                }
            }
        }
        inicjalizacjaRamek = 0;
        while(liczbaWykonanychOdwolan<ciag.getCiag().size()){
            boolean czyBlad = true;
            for(int i=0; i<ramki.length; i++){
                if(ciag.getCiag().get(liczbaWykonanychOdwolan).equals(ramki[i])){
                    czyBlad=false;
                    break;
                }
            }
            if(czyBlad){
                Random r = new Random();
                int r1 = r.nextInt(ramki.length);
                ramki[r1] = ciag.getCiag().get(liczbaWykonanychOdwolan);
                liczbaBledow++;
            }
            liczbaWykonanychOdwolan++;
        }

        return liczbaBledow;
    }


    public static void main(String[] args){
        int liczbaRamek = 5;
        int liczbaStron = 50;
        int liczbaOdwolan = 10000;

        Algorytmy alg = new Algorytmy(liczbaRamek);

        CiagOdwolan ciag1 = alg.generate(liczbaStron, liczbaOdwolan);

        System.out.println("FIFO: " +alg.FIFO(ciag1));
        System.out.println("OPT: " +alg.OPT(ciag1));
        System.out.println("LRU: " +alg.LRU(ciag1));
        System.out.println("LRU APX: " +alg.LRUAPX(ciag1));
        System.out.println("RAND: " +alg.RAND(ciag1));
    }


}
