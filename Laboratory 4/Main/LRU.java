package Main;

import Procesy.Proces;
import Procesy.Strona;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LRU {

    public int LRUProcentowy(List<Proces> ciag, List<Strona> globalnyCiagOdwolan){
        ArrayList<Strona[]> ramki = new ArrayList<>();
        ArrayList<Integer> ileOdwolanWykonano = new ArrayList<>();
        ArrayList<Boolean> czyZainicjalizowaneRamki = new ArrayList<>();

        for(Proces x: ciag){
            x.setLiczbaBledow(0);
        }

        int liczbaBledow = 0;

        double max = 0.9;
        double min = 0.2;

        for(Proces x: ciag){
            ramki.add(new Strona[x.getLiczbaRamek()]);
            ileOdwolanWykonano.add(0);
            czyZainicjalizowaneRamki.add(false);
        }
        while(!globalnyCiagOdwolan.isEmpty()){
            Strona aktualna = globalnyCiagOdwolan.get(0);
            Proces aktualnyProces = ciag.get(aktualna.getIndeks()-1);
            Strona[] aktualnaRamka = ramki.get(aktualna.getIndeks()-1);

            if(!czyZainicjalizowaneRamki.get(aktualna.getIndeks()-1)){
                for(int i=0; i<aktualnaRamka.length; i++){
                    if(aktualnaRamka[i]==null){
                        aktualnaRamka[i] = aktualna;
                        ileOdwolanWykonano.set(aktualna.getIndeks()-1, ileOdwolanWykonano.get(aktualna.getIndeks()-1)+1);
                        aktualnyProces.setLiczbaBledow(aktualnyProces.getLiczbaBledow()+1);
                        globalnyCiagOdwolan.remove(0);
                        if(i == aktualnaRamka.length-1){
                            czyZainicjalizowaneRamki.set(aktualna.getIndeks()-1, true);
                        }
                        break;

                    }
                    else if(aktualnaRamka[i].equals(aktualna)){
                        ileOdwolanWykonano.set(aktualna.getIndeks()-1, ileOdwolanWykonano.get(aktualna.getIndeks()-1)+1);
                        globalnyCiagOdwolan.remove(0);
                        break;
                    }
                }
            }
            else{
                boolean czyBlad = true;
                for (int i = 0; i < aktualnaRamka.length; i++) {
                    if (aktualna.equals(aktualnaRamka[i])) {
                        czyBlad = false;
                        break;
                    }
                }
                if (czyBlad) {
                    ArrayList<Strona> temp = new ArrayList<>();
                    int tempStan = ileOdwolanWykonano.get(aktualna.getIndeks()-1);
                    temp.addAll(Arrays.asList(aktualnaRamka));
                    while (temp.size() != 1 && tempStan > 0) {
                        for (Strona x : temp) {
                            if (ciag.get(aktualna.getIndeks()-1).getCiagOdwolan().get(tempStan).equals(x)) {
                                temp.remove(x);
                                break;
                            }
                        }
                        tempStan--;
                    }
                    if (temp.size() != 1) {
                        for (int i = 1; i < temp.size(); i++) {
                            temp.remove(i);
                        }
                    }
                    for (int i = 0; i < aktualnaRamka.length; i++) {
                        if (aktualnaRamka[i].equals(temp.get(0))) {
                            aktualnaRamka[i] = ciag.get(aktualna.getIndeks()-1).getCiagOdwolan().get(ileOdwolanWykonano.get(aktualna.getIndeks()-1));
                        }
                    }
                    aktualnyProces.setLiczbaBledow(aktualnyProces.getLiczbaBledow()+1);
                }
                ileOdwolanWykonano.set(aktualna.getIndeks()-1, ileOdwolanWykonano.get(aktualna.getIndeks()-1)+1);
                globalnyCiagOdwolan.remove(0);

                double ratio = (aktualnyProces.getLiczbaBledow()*100.00/ileOdwolanWykonano.get(aktualna.getIndeks()-1))/100;

                if(ratio>max){
                    Strona[] ramkiNowe = new Strona[ramki.get(aktualna.getIndeks()-1).length+1];
                    for(int i=0; i<ramki.get(aktualna.getIndeks()-1).length; i++){
                        ramkiNowe[i] = ramki.get(aktualna.getIndeks()-1)[i];
                    }
                    ramki.set(aktualna.getIndeks()-1, ramkiNowe);
                    czyZainicjalizowaneRamki.set(aktualna.getIndeks()-1, false);
                }
                else if(ratio<min){
                    if(ramki.get(aktualna.getIndeks()-1).length>1){
                        Strona[] ramkiNowe = new Strona[ramki.get(aktualna.getIndeks()-1).length-1];
                        for(int i=0; i<ramkiNowe.length; i++){
                            ramkiNowe[i] = ramki.get(aktualna.getIndeks()-1)[i];
                        }
                        ramki.set(aktualna.getIndeks()-1, ramkiNowe);
                    }
                }
            }

        }
        for(Proces x: ciag){
            liczbaBledow+=x.getLiczbaBledow();
        }
        return liczbaBledow;
    }

    public void LRUdynamiczne(Proces proces, List<Strona> ciag){
        int liczbaBledow = 0;
        Strona[] ramki = new Strona[proces.getLiczbaRamek()];

        int liczbaWykonanychOdwolan = 0;

        int inicjalizacjaRamek = 0;
        while (ramki[ramki.length - 1] == null && liczbaWykonanychOdwolan < ciag.size()) {
            if (ramki[0] == null) {
                ramki[0] = ciag.get(liczbaWykonanychOdwolan);
                liczbaWykonanychOdwolan++;
                inicjalizacjaRamek++;
                liczbaBledow++;
            } else {
                for (int i = inicjalizacjaRamek; i > 0; i--) {
                    if (ramki[i - 1].equals(ciag.get(liczbaWykonanychOdwolan))) {
                        liczbaWykonanychOdwolan++;
                        break;
                    }
                    if (i == 1 && !ramki[0].equals(ciag.get(liczbaWykonanychOdwolan))) {
                        ramki[inicjalizacjaRamek] = ciag.get(liczbaWykonanychOdwolan);

                        liczbaWykonanychOdwolan++;
                        inicjalizacjaRamek++;
                        liczbaBledow++;
                    }
                }
            }
        }
        while (liczbaWykonanychOdwolan < ciag.size()) {
            boolean czyBlad = true;
            for (int i = 0; i < ramki.length; i++) {
                if (ciag.get(liczbaWykonanychOdwolan).equals(ramki[i])) {
                    czyBlad = false;
                    break;
                }
            }
            if (czyBlad) {
                ArrayList<Strona> temp = new ArrayList<>();
                int tempStan = liczbaWykonanychOdwolan;
                temp.addAll(Arrays.asList(ramki));
                while (temp.size() != 1 && tempStan > 0) {
                    for (Strona x : temp) {
                        if (ciag.get(tempStan).equals(x)) {
                            temp.remove(x);
                            break;
                        }
                    }
                    tempStan--;
                }
                if (temp.size() != 1) {
                    for (int i = 1; i < temp.size(); i++) {
                        temp.remove(i);
                    }
                }
                for (int i = 0; i < ramki.length; i++) {
                    if (ramki[i].equals(temp.get(0))) {
                        ramki[i] = ciag.get(liczbaWykonanychOdwolan);
                    }
                }
                liczbaBledow++;
            }
            liczbaWykonanychOdwolan++;
        }
        proces.dodajBledy(liczbaBledow);
    }

    public int LRU(ArrayList<Proces> list){
        int liczbaBledow = 0;
        for(int z=0; z<list.size(); z++) {
            Strona[] ramki = new Strona[list.get(z).getLiczbaRamek()];

            int liczbaWykonanychOdwolan = 0;

            int inicjalizacjaRamek = 0;
            while (ramki[ramki.length - 1] == null && liczbaWykonanychOdwolan < list.get(z).getCiagOdwolan().size()) {
                if (ramki[0] == null) {
                    ramki[0] = list.get(z).getCiagOdwolan().get(liczbaWykonanychOdwolan);
                    liczbaWykonanychOdwolan++;
                    inicjalizacjaRamek++;
                    liczbaBledow++;
                } else {
                    for (int i = inicjalizacjaRamek; i > 0; i--) {
                        if (ramki[i - 1].equals(list.get(z).getCiagOdwolan().get(liczbaWykonanychOdwolan))) {
                            liczbaWykonanychOdwolan++;
                            break;
                        }
                        if (i == 1 && !ramki[0].equals(list.get(z).getCiagOdwolan().get(liczbaWykonanychOdwolan))) {
                            ramki[inicjalizacjaRamek] = list.get(z).getCiagOdwolan().get(liczbaWykonanychOdwolan);

                            liczbaWykonanychOdwolan++;
                            inicjalizacjaRamek++;
                            liczbaBledow++;
                        }
                    }
                }
            }
            while (liczbaWykonanychOdwolan < list.get(z).getCiagOdwolan().size()) {
                boolean czyBlad = true;
                for (int i = 0; i < ramki.length; i++) {
                    if (list.get(z).getCiagOdwolan().get(liczbaWykonanychOdwolan).equals(ramki[i])) {
                        czyBlad = false;
                        break;
                    }
                }
                if (czyBlad) {
                    ArrayList<Strona> temp = new ArrayList<>();
                    int tempStan = liczbaWykonanychOdwolan;
                    temp.addAll(Arrays.asList(ramki));
                    while (temp.size() != 1 && tempStan > 0) {
                        for (Strona x : temp) {
                            if (list.get(z).getCiagOdwolan().get(tempStan).equals(x)) {
                                temp.remove(x);
                                break;
                            }
                        }
                        tempStan--;
                    }
                    if (temp.size() != 1) {
                        for (int i = 1; i < temp.size(); i++) {
                            temp.remove(i);
                        }
                    }
                    for (int i = 0; i < ramki.length; i++) {
                        if (ramki[i].equals(temp.get(0))) {
                            ramki[i] = list.get(z).getCiagOdwolan().get(liczbaWykonanychOdwolan);
                        }
                    }
                    liczbaBledow++;
                }
                liczbaWykonanychOdwolan++;
            }
        }
        return liczbaBledow;
    }
}
