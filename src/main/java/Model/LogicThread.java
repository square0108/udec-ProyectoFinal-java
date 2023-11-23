package Model;

import java.util.ArrayList;

public class LogicThread implements Runnable {
    protected ArrayList<Habitat> habitats;

    public LogicThread(ArrayList<Habitat> allHabitats) {
        this.habitats = allHabitats;
    }

    @Override
    public void run() {
        while(true) {
            for (Habitat habitat : habitats) {
                habitat.actualizarHambreAnimales();
                habitat.removerAnimalesMuertos();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
