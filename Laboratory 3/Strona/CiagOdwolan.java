package Strona;

import java.util.ArrayList;
import java.util.Random;

public class CiagOdwolan {

    private ArrayList<Strona> ciag;
    private int liczbaOdwolan;
    private PamiecLogiczna pamiec;

    public CiagOdwolan(int liczbaOdwolan, PamiecLogiczna pamiec){
        this.liczbaOdwolan = liczbaOdwolan;
        this.pamiec = pamiec;

        ciag = new ArrayList<>();

        Random random = new Random();
        int random1 = random.nextInt(pamiec.getPamiec().size());

        ciag.add(new Strona(pamiec.getPamiec().get(random1).getWartoscOdwolania()));
        for(int i=1; i<liczbaOdwolan; i++){
            Random rand = new Random();
            int r;
            if(pamiec.getPamiec().size()<10){
                r = rand.nextInt(-1,2);
            }
            else{
                r = rand.nextInt(-3,4);
            }

            int wart = ciag.get(i-1).getWartoscOdwolania()+r;
            if(wart<0){
                wart = -wart;
            }
            if(wart>pamiec.getPamiec().size()-1){
                wart = wart-2*r;
            }
            ciag.add(new Strona(wart));
        }
    }

    public ArrayList<Strona> getCiag() {
        return ciag;
    }

    public void setCiag(ArrayList<Strona> ciag) {
        this.ciag = ciag;
    }

    public int getLiczbaOdwolan() {
        return liczbaOdwolan;
    }

    public void setLiczbaOdwolan(int liczbaOdwolan) {
        this.liczbaOdwolan = liczbaOdwolan;
    }


    public PamiecLogiczna getPamiec() {
        return pamiec;
    }

    public void setPamiec(PamiecLogiczna pamiec) {
        this.pamiec = pamiec;
    }
}
