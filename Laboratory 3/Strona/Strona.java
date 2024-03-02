package Strona;

import java.util.Objects;

public class Strona {



    private int wartoscOdwolania;
    private int bitOdwolania;

    public Strona(int wartoscOdwolania) {
        this.wartoscOdwolania = wartoscOdwolania;
        this.bitOdwolania = 1;
    }



    public int getWartoscOdwolania() {
        return wartoscOdwolania;
    }

    public void setWartoscOdwolania(int wartoscOdwolania) {
        this.wartoscOdwolania = wartoscOdwolania;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strona strona = (Strona) o;
        return wartoscOdwolania == strona.wartoscOdwolania;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wartoscOdwolania);
    }

    public int getBitOdwolania() {
        return bitOdwolania;
    }

    public void setBitOdwolania(int bitOdwolania) {
        this.bitOdwolania = bitOdwolania;
    }
}

