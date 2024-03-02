package Procesy;

import java.util.ArrayList;

public class PamiecLogiczna {

    private ArrayList<Strona> pamiec;

    public PamiecLogiczna(){
        pamiec = new ArrayList<>();
    }

    public ArrayList<Strona> getPamiec() {
        return pamiec;
    }

    public void setPamiec(ArrayList<Strona> pamiec) {
        this.pamiec = pamiec;
    }
}
