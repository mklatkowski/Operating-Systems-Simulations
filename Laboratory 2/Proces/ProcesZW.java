package Proces;

public class ProcesZW extends Proces{

    private int dlugoscFazy;
    private int pozycja;

    public ProcesZW(int dlugoscFazy) {
        super(dlugoscFazy);
    }
    public ProcesZW(int dlugoscFazy, int pozycja){
        super(dlugoscFazy, pozycja);
    }
    public ProcesZW(){
        super();
    }
}
