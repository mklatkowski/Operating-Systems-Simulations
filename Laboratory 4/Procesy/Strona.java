package Procesy;

import java.util.Objects;

public class Strona {

    private int wartoscOdwolania;
    private int indeks;

    public Strona(int wartoscOdwolania, int indeks) {
        this.wartoscOdwolania = wartoscOdwolania;
        this.indeks = indeks;
    }

    public int getWartoscOdwolania() {
        return wartoscOdwolania;
    }

    public void setWartoscOdwolania(int wartoscOdwolania) {
        this.wartoscOdwolania = wartoscOdwolania;
    }

    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) {
        this.indeks = indeks;
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

    @Override
    public String toString() {
        return "Strona{" +
                "wartoscOdwolania=" + wartoscOdwolania +
                ", indeks=" + indeks +
                '}';
    }
}