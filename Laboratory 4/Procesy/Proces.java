package Procesy;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Proces {

    private static int indeks=1;

    private ArrayList<Strona> pamiec;
    private int liczbaRamek;
    private ArrayList<Strona> ciagOdwolan;
    private int liczbaBledow;

    private void generujPamiec(int max){
        while(max<2){
            max++;
        }
        Random r = new Random();
        int rr = r.nextInt(1,max);
        for(int i=0; i<rr; i++){
            pamiec.add(new Strona(i, indeks));
        }
    }

    private void generujOdwolania(int maxOdwolania){
        Random random111 = new Random();
        int random11 = random111.nextInt(pamiec.size());

        ciagOdwolan.add(new Strona(random11, indeks));

        Random random = new Random();
        int rand = random.nextInt(maxOdwolania/2, maxOdwolania);
        for(int i=1; i<rand; i++){
            Random rand1 = new Random();
            int r;
            if(pamiec.size()<10){
                r = rand1.nextInt(-1,2);
            }
            else{
                r = rand1.nextInt(-3,4);
            }

            int wart = ciagOdwolan.get(i-1).getWartoscOdwolania()+r;
            if(wart<0){
                wart = -wart;
            }
            if(wart>pamiec.size()-1){
                wart = wart-2*r;
            }
            ciagOdwolan.add(new Strona(wart, indeks));
        }
    }

    public Proces(int max, int maxOdwolania){
        pamiec = new ArrayList<>();
        ciagOdwolan = new ArrayList<>();
        generujPamiec(max);
        generujOdwolania(maxOdwolania);
        indeks++;
        liczbaBledow = 0;
    }

    public static int getIndeks() {
        return indeks;
    }

    public static void setIndeks(int indeks) {
        Proces.indeks = indeks;
    }

    public ArrayList<Strona> getPamiec() {
        return pamiec;
    }

    public void setPamiec(ArrayList<Strona> pamiec) {
        this.pamiec = pamiec;
    }

    public int getLiczbaRamek() {
        return liczbaRamek;
    }

    public void setLiczbaRamek(int liczbaRamek) {
        this.liczbaRamek = liczbaRamek;
    }

    public ArrayList<Strona> getCiagOdwolan() {
        return ciagOdwolan;
    }

    public void setCiagOdwolan(ArrayList<Strona> ciagOdwolan) {
        this.ciagOdwolan = ciagOdwolan;
    }

    public int getLiczbaBledow(){
        return liczbaBledow;
    }
    public void setLiczbaBledow(int liczbaBledow){
        this.liczbaBledow = liczbaBledow;
    }

    public void dodajBledy(int bledy){
        this.setLiczbaBledow(this.getLiczbaBledow()+bledy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proces proces = (Proces) o;
        return liczbaRamek == proces.liczbaRamek && Objects.equals(pamiec, proces.pamiec) && Objects.equals(ciagOdwolan, proces.ciagOdwolan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pamiec, liczbaRamek, ciagOdwolan);
    }
}
