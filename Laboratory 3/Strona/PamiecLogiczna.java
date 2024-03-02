package Strona;

import java.util.ArrayList;

public class PamiecLogiczna {

    private ArrayList<Strona> pamiec;
    private int iloscStron;

    public PamiecLogiczna(int iloscStron){
        pamiec = new ArrayList<>();
        for(int i=0; i<iloscStron; i++){
            pamiec.add(new Strona(i));
        }
    }

    public ArrayList<Strona> getPamiec() {
        return pamiec;
    }

    public void setPamiec(ArrayList<Strona> pamiec) {
        this.pamiec = pamiec;
    }
}
