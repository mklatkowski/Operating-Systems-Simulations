package RT;

import Proces.ProcesRT;

import java.util.ArrayList;

public interface RT{

    int obecnyCzas(ArrayList<ProcesRT> ciag, int pozycjaPoczatkowa);
    int calkowityCzas(ArrayList<ProcesRT> ciag, int pozycjaPoczatkowa, int max);

}

