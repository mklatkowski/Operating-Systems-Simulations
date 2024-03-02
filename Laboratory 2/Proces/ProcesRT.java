package Proces;

public class ProcesRT extends Proces{
    private int etykieta;

    public ProcesRT(int dlugoscFazy, int etykieta){
        super(dlugoscFazy);
        this.etykieta = etykieta;
    }
    public ProcesRT(int dlugoscFazy, int pozycja, int etykieta){
        super(dlugoscFazy, pozycja);
        this.etykieta = etykieta;
    }
    public ProcesRT(){
        super();
    }

    public int getEtykieta() {
        return etykieta;
    }

    public void setEtykieta(int etykieta) {
        this.etykieta = etykieta;
    }

    @Override
    public String toString() {
        return "ProcesRT{" + super.toString() +
                "etykieta=" + etykieta +
                '}';
    }
}
