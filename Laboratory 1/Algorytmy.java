package Proces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Algorytmy {

    public static ArrayList<Proces> Generate(){
        ArrayList<Proces> procesy = new ArrayList<>();

//        procesy.add(new Proces(1, 20, 1));
//        procesy.add(new Proces(2, 5, 2));
//        procesy.add(new Proces(3, 3, 12));
//        procesy.add(new Proces(4, 14, 25));
//        procesy.add(new Proces(5, 7, 100));
//
//        procesy.add(new Proces(1, 2, 1));
//        procesy.add(new Proces(2, 5, 2));
//        procesy.add(new Proces(3, 8, 12));
//        procesy.add(new Proces(4, 13, 25));
//        procesy.add(new Proces(5, 18, 100));
//
        procesy.add(new Proces(1, 5, 1));
        procesy.add(new Proces(2, 3, 2));
        procesy.add(new Proces(3, 8, 3));
        procesy.add(new Proces(4, 4, 4));
        procesy.add(new Proces(5, 17, 5));
        procesy.add(new Proces(6, 7, 7));
        procesy.add(new Proces(7, 10, 9));
        procesy.add(new Proces(8, 12, 13));
        procesy.add(new Proces(9, 1, 14));
        procesy.add(new Proces(10, 8, 15));
        procesy.add(new Proces(11, 19, 16));
        procesy.add(new Proces(12, 31, 18));
        procesy.add(new Proces(13, 7, 22));
        procesy.add(new Proces(14, 17, 24));
        procesy.add(new Proces(15, 16, 25));
        procesy.add(new Proces(16, 23, 26));
        procesy.add(new Proces(17, 1, 28));
        procesy.add(new Proces(18, 4, 30));
        procesy.add(new Proces(19, 4, 31));
        procesy.add(new Proces(20, 7, 34));
        procesy.add(new Proces(21, 18, 35));
        procesy.add(new Proces(22, 9, 36));
        procesy.add(new Proces(23, 4, 40));
        procesy.add(new Proces(24, 6, 45));
        procesy.add(new Proces(25, 11, 48));
        procesy.add(new Proces(26, 13, 49));
        procesy.add(new Proces(27, 5, 50));
        procesy.add(new Proces(28, 4, 52));
        procesy.add(new Proces(29, 13, 54));
        procesy.add(new Proces(30, 9, 58));



        return procesy;
    }


    public static double FCFS(ArrayList<Proces> procesy) {
        int calkowityCzas = 0;

        Comparator2 comp = new Comparator2();
        Collections.sort(procesy, comp); //sortowanie procesów względem momentu zgłoszenia

        int aktualnyCzas = procesy.get(0).getMomZgloszenia()+procesy.get(0).getDlFazyProcesora();


        for (int i = 1; i < procesy.size(); i++) { // wyznaczanie czasu oczekiwania wszystkich elementów poza 1 - ten będzie miał czas oczekiwania 0
            if(procesy.get(i).getMomZgloszenia()>aktualnyCzas){
                aktualnyCzas = procesy.get(i).getMomZgloszenia()+procesy.get(i).getDlFazyProcesora();
            }
            else{
                procesy.get(i).setCzasOczekiwania(aktualnyCzas-procesy.get(i).getMomZgloszenia());
                aktualnyCzas+=procesy.get(i).getDlFazyProcesora();
            }
////            int czasTemp = procesy.get(0).getMomZgloszenia(); // początek "osi czasu" jako moment zgłoszenia pierwszego elementu
//            for (int j = i - 1; j >= 0; j--) { // dodanie do osi czasu wszystkich długości procesów, które są przed danym elementem
//                czasTemp += procesy.get(j).getDlFazyProcesora();
//            }
//            czasTemp = czasTemp - procesy.get(i).getMomZgloszenia(); // od czasu odejmowany jest moment zgłoszenia
//            // w tym momencie są możliwe dwie opcje:
//            //1 -moment dołączenia procesu znajdował się w trakcie wykonywania jakiegokolwiek procesu: wtedy czas oczekiwania będzie równy jak powyżej
//            // 2- przeciwnie, wtedy czas oczekiwania jest równy 0, a czasTempjest <=0
//            if (czasTemp > 0) {
//                procesy.get(i).setCzasOczekiwania(czasTemp);
//            }

        }
        for (Proces x : procesy) {
//            System.out.println(x.getCzasOczekiwania());
            calkowityCzas += x.getCzasOczekiwania();
        }
        return (double) calkowityCzas / procesy.size();
    }

    public static double SJF(ArrayList<Proces> procesy) {

        int calkowityCzas = 0;
        int indeksProces = 1; // licznik procesów, które są przyjęte do realizacji - weszły już do listy temp
        int indeksZakonczone = 1;

        ArrayList<Proces> temp = new ArrayList<>();

        Comparator1 comp1 = new Comparator1();
        Comparator2 comp = new Comparator2();
        Collections.sort(procesy, comp);

        int aktualnyCzas = procesy.get(0).getMomZgloszenia() + procesy.get(0).getDlFazyProcesora(); // aktualny czas na osi czasu, na początek ustawiony na
        // moment po wykonaniu pierwszego procesu

        while (indeksZakonczone != procesy.size()) {

            for (int i = indeksProces; i < procesy.size(); i++) { // jeśli w trakcie wykonywania i-tego procesu doszedł nowy proces, zostaje on dołączony do
                // tymczasowej listy, która po wykonaniu i-tego procesu będzie sortowana
                if (procesy.get(i).getMomZgloszenia() < aktualnyCzas ) {
                    int czasTemp = aktualnyCzas - procesy.get(i).getMomZgloszenia(); // wyznaczony jest wtedy czas oczekiwania jaki proces dotychczas musiał odczekać
                    // czyli od momentu zgłoszenia do aktualnego czasu
                    procesy.get(i).setCzasOczekiwania(czasTemp);
                    temp.add(procesy.get(i));
                    indeksProces++;
                }
            }
            if (temp.size() != 0) { // jeśli lista tymczasowa nie jest pusta, to po wykonaniu i-tego procesu jest sortowana.
                Collections.sort(temp, comp1);
                aktualnyCzas += temp.get(0).getDlFazyProcesora(); // następnym momentem który trzeba odnotować do aktualnego czasu jest czas wykonania następnego procesu w
                // liście temp - skoro moment dodania był w trakcie wykonywania poprzedniego procesu, to po nim zacznie się wykonywać od razu,a do aktualnego czasu zostanie dodana
                // długość tego procesu
                indeksZakonczone++;
                for (int i = 1; i < temp.size(); i++) { // dla reszty elementów z listy oczekującej dodawany jest czas wykonania pierwszego elementu w listy tymczasowej
                    temp.get(i).setCzasOczekiwania(aktualnyCzas - temp.get(i).getMomZgloszenia());
                }
                temp.remove(0); // po wykonaniu procesu, jest on usuwany z listy procesów do wykonania
            }
            else { // jeśli lista tymczasowa jest pusta, to procesor czeka, aż zgłosi się kolejny proces, dlatego: aktualnyCzas = procesy.get(indeksProces).getMomZgloszenia();
                aktualnyCzas = procesy.get(indeksProces).getMomZgloszenia()+procesy.get(indeksProces).getDlFazyProcesora();
                procesy.get(indeksProces).setCzasOczekiwania(0);// skoro ten proces nie oczekiwał (w liście do wykonania nie było nic) to czas oczekiwania jest równy 0
                indeksProces++; // liczba procesów które zostały podjęte do realizacji zwiększa się.
                indeksZakonczone++;
            }
        }
        for(Proces x: procesy) {
            calkowityCzas += x.getCzasOczekiwania();
        }

        return (double) calkowityCzas/procesy.size();
    }

    public static double SJFW(ArrayList<Proces> procesy){

        int czasCalkowity = 0;
        boolean KoniecznyNowyProcesBoolean;

        int indeksProces = 0; // liczba zakonczonych procesow
        int indeksTemp = 1; // liczba procesow, ktore znalazly sie w temp

        ArrayList<Proces> temp = new ArrayList<>();

        Comparator1 comp1 = new Comparator1();
        Comparator2 comp = new Comparator2();

        Collections.sort(procesy, comp);

        int aktualnyCzas=procesy.get(0).getMomZgloszenia(); // oś czasu ustawiona na moment rozpoczęcia pierwszego procesu

        temp.add(procesy.get(0));

        while(indeksProces!=procesy.size()){

            int roznica = aktualnyCzas;

            // dygresja, zmiana procesu może odbyć się na dwa sposoby, albo zakończył się on, albo doszedł nowy (wtedy należy posortować)

            int ProcesSieZakonczyl;
            int KoniecznyNowyProces;
            if(temp.size()==0){ // jeśli w kolejce nie ma żadnych procesów, to bierzemy następny z listy procesów i jego moment zgłoszenia ustalamy na aktualy czas
                KoniecznyNowyProces = procesy.get(indeksTemp).getMomZgloszenia();
                aktualnyCzas = KoniecznyNowyProces;
            }
            else if(indeksTemp == procesy.size()){ // jeśli zgłoszenie gotowości do realizacji zgłosiły już wszystkie procesy, to wszytkie elementy elemeny z kolejki muszą być wykonane w kolejności
                ProcesSieZakonczyl = temp.get(0).getDlFazyProcesora()+aktualnyCzas;
                KoniecznyNowyProces = 0;
                aktualnyCzas = ProcesSieZakonczyl;
            }
            else if(indeksTemp < procesy.size()){ // jeśli nie jest jak powyżej, to do ewentualnego przełączenia wątku (do sprawdzenia) należy wziąć moment PSZ lub KNP
                ProcesSieZakonczyl = temp.get(0).getDlFazyProcesora()+aktualnyCzas;
                KoniecznyNowyProces = procesy.get(indeksTemp).getMomZgloszenia();
                aktualnyCzas = Math.min(ProcesSieZakonczyl, KoniecznyNowyProces);
            }
            else{
                ProcesSieZakonczyl = temp.get(0).getDlFazyProcesora()+aktualnyCzas;
                KoniecznyNowyProces = 0;
                aktualnyCzas = ProcesSieZakonczyl;;
            }
            roznica = aktualnyCzas - roznica; // określamy różnice pomiędzy obecnym a poprzednim momentem zmiany/porównania procesów

            if(aktualnyCzas == KoniecznyNowyProces){// w trakcie wykonywania procesu doszedł inny, dodawany jest do kolejki
                if(indeksTemp<procesy.size()){
                    temp.add(procesy.get(indeksTemp));
                    indeksTemp++;
                }
                temp.get(0).setDlFazyProcesora(temp.get(0).getDlFazyProcesora()-roznica);// do momentu ewentualnej zmiany, długość fazy procesora która została
                // do wykonania zmniejsza się
                Collections.sort(temp, comp1);
                if(temp.size()>2){
                    for(int i=2; i<temp.size(); i++){ // jeden proces był w trakcie wykonywania, drugi dopiero doszedł, zatem do reszty trzeba dodać roznice czasu
                        temp.get(i).setCzasOczekiwania(temp.get(i).getCzasOczekiwania()+roznica);
                    }
                }
            }
            else { // jesli do konca wykonania sie procesu zaden nie doszedl, to procesy w kolejce czekaly czas wykonywania sie procesu
               for(int i=1; i<temp.size(); i++){
                   temp.get(i).setCzasOczekiwania(temp.get(i).getCzasOczekiwania()+roznica);
//                   System.out.println("Obecny czas jakiś dodany: " + temp.get(i).getCzasOczekiwania());
               }
               temp.remove(0);// po wykonaniu sie procesu jest on wyrzucany z kolejki
               indeksProces++; // liczba zakonczonych procesow wzrasta
            }
//            System.out.println("Aktualny czas: " +aktualnyCzas);
        }

        for(Proces x: procesy){
            czasCalkowity+=x.getCzasOczekiwania();
//            System.out.println(x.getCzasOczekiwania());
        }


        return (double) czasCalkowity/procesy.size();
    }

    public static double RR(ArrayList<Proces> procesy, int kwantCzasu){

        int czasCalkowity = 0;
        int rozpoczeteProcesy = 1; // procesy, które się zaczęły
        int indeksProces = 0; // procesy zakończone
        int indeksTemp=1; // procesy znajdujące się obecnie w kolejnce

        Comparator2 comp = new Comparator2();

        Collections.sort(procesy, comp); // sortowanie względem momentów dołączenia

        int aktualnyCzas = procesy.get(0).getMomZgloszenia(); // początek osi czasu na moment zgłoszenia pierwszego procesu

//        System.out.println("Aktualny czas:" +aktualnyCzas);

        ArrayList<Proces> temp = new ArrayList<>();

        temp.add(procesy.get(0)); // dodanie do kolejki procesu z pierwszym momentem zgłoszenia

        while(indeksProces!=procesy.size()){ // dopóki wszystkie procesy nie będą wykonane
            if(temp.size()==0){ // jeśli w kolejce nie ma procesów, to przesuwamy się do momentu, w którym następny proces się zgłosi
                aktualnyCzas = procesy.get(indeksProces).getMomZgloszenia();
//                System.out.println("Aktualny czas: " + aktualnyCzas);
                temp.add(procesy.get(indeksProces));//dodajemy wcześniej wspomniany proces do kolejki
                rozpoczeteProcesy++;
                // nie ustawiamy z góry czasu oczekiwania na 0, gdyż jest to algorytm wywłaszczający
            }

            for(int i=0;i<temp.size(); i++){ // pętla tzw cykli (ja zdefiniowałem je sobie jako wykonanie części procesów od procesu pierwszego do ostatniego w kolejce (w międzyczasie za
                // wykonaną część procesu może dojść kolejny proces

                boolean b=true; // zmienna która określa, czy przy wykonaniu części procesu, doszedł jakiś kolejny do cyklu, który trzeba wykonać

                int roznica = Math.min(kwantCzasu, temp.get(i).getDlFazyProcesora()); // przerwanie procesu może dojść albo gdzy wykona się cały kwart czasu, albo jak proces zostanie zakończony


                temp.get(i).setDlFazyProcesora(temp.get(i).getDlFazyProcesora()-roznica); // po wykonaniu kwartu/ zakończeniu odejmowany jest pozostały czas dlugosci fazy

//                System.out.println("Czas fazy: " +temp.get(i).getDlFazyProcesora());

                aktualnyCzas+=roznica; // do czasu aktualnego jest dodawany wcześniej wspomniany czas

//                System.out.println("Aktualny czas: " +aktualnyCzas);

                do{ // pętla która sprawdza, czy w trakcie wykonywania części procesu nie doszedł jakiś nowy proces
                    // do zatrzymania pętli potrzebna będzie zmienna typu boolean  - b.
                    if(rozpoczeteProcesy<procesy.size()){ // pobierany jest poniżej indeks rozpoczeteProcesy, więc nie może być równy liczbie wszystkich procesów

                        if(aktualnyCzas>=procesy.get(rozpoczeteProcesy).getMomZgloszenia()){ // jeśli w trakcie wykonywania poprzedniego procesu doszedł jakiś nowy

                            temp.add(procesy.get(rozpoczeteProcesy)); // dodawany jest do kolejki

                            indeksTemp++; // liczba elementów w kolejce wzrasta
                            rozpoczeteProcesy++; // tak jak liczba rozpoczętych procesów

                            temp.get(indeksTemp-1).setCzasOczekiwania(temp.get(indeksTemp-1).getCzasOczekiwania()+aktualnyCzas-temp.get(indeksTemp-1).getMomZgloszenia()); // czas oczekiwania
                            // tegoż właśnie elementu jako poprzedni czas oczekiwania + aktualny czas - moment zgłoszenia (wzór podobny jak przy kolejce FIFO)

//                            System.out.println("Czas fazy: " +temp.get(indeksTemp-1).getDlFazyProcesora());

                            roznica = Math.min(kwantCzasu, temp.get(indeksTemp-1).getDlFazyProcesora()); // podobnie jak wczesniej, określany jest czas obsługi tegoż procesu

                            temp.get(indeksTemp-1).setDlFazyProcesora(temp.get(indeksTemp-1).getDlFazyProcesora()-roznica); // i dla tego procesu zmniejszany odpowiednio pozostała długość fazy

                            aktualnyCzas+=roznica; // do aktualnego czasu dodana różnica (czas wykonywania tego procesu);
//                            System.out.println("Aktualny czas: " +aktualnyCzas);

                            for(int k=0; k<temp.size(); k++){
                                if((temp.get(k) != temp.get(indeksTemp-1)) && temp.get(k)!=temp.get(i)){
                                    temp.get(k).setCzasOczekiwania(temp.get(k).getCzasOczekiwania()+roznica);// dla każdego innego czasu oprócz tego jest odpowiednio dodawany
                                    // czas oczekiwania poszerzony o roznice wykonania tego procesu
                                }
                            }
                            if(temp.get(indeksTemp-1).getDlFazyProcesora()<=0){ // jeśli dany element został wykonany, to naturalnie, jest usuwany z kolejki
                                temp.remove(indeksTemp-1);
                                indeksProces++;
                                indeksTemp--;
                            }
                            else{
                                i++;
                            }
                        }
                        else{
                            b = false;
                        }
                    }
                    else{
                        b = false;
                    }
                }while(b);// jeśli w tym czasie nie dołączają już żadne procesy, to w tym cyklu liczba procesów się już nie zwiększy

                for(int k=0; k<temp.size(); k++){
                    if(temp.get(k)!= temp.get(i)){
                        temp.get(k).setCzasOczekiwania(temp.get(k).getCzasOczekiwania()+roznica); // czas oczekiwania poza obecnie wykonywanym procesem jest zwiększany
                    }
                }
                if(temp.get(i).getDlFazyProcesora()<=0){ // jeśli jakiś proces się wykona, to jest usuwany z kolejki
                    temp.remove(i);
                    indeksProces++;
                    indeksTemp--;
                }
                else{
                    temp.get(i).setCzasOczekiwania(temp.get(i).getCzasOczekiwania()+kwantCzasu); // jeśli nie jest usunięty, to dodatkowo dodawany jest czas oczekiwania
                    // proces się nie zakończył, więc na zakończenie dalej oczekuje.
                }
            }
//            System.out.println("Cykl zakończony");
        }

        for(Proces x: procesy){
            czasCalkowity+=x.getCzasOczekiwania();
//            System.out.println(x.getCzasOczekiwania());
        }


        return (double) czasCalkowity/procesy.size();

    }

    public static void main(String[] args) {

        ArrayList<Proces> procesy = Generate();
        ArrayList<Proces> procesy1 = Generate();
        ArrayList<Proces> procesy2 = Generate();
        ArrayList<Proces> procesy3 = Generate();

        System.out.println("Wynik FCFS: " +FCFS(procesy));
        System.out.println("Wynik SJF: " +SJF(procesy1));
        System.out.println("Wynik SJFW: " +SJFW(procesy2));
        System.out.println("Wynik RR: " +RR(procesy3, 1));


    }

    static class Comparator1 implements Comparator<Proces> {
        public int compare(Proces o1, Proces o2) {
            return Integer.compare(o1.getDlFazyProcesora(), o2.getDlFazyProcesora());
        }
    }

    static class Comparator2 implements Comparator<Proces> {
        public int compare(Proces o1, Proces o2) {
            return Integer.compare(o1.getMomZgloszenia(), o2.getMomZgloszenia());
        }
    }
}
