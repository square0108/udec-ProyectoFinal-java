package Model.Especies;

import java.util.TimerTask;

public class HambreTicker extends TimerTask {
    protected Animal animal;
    public HambreTicker(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void run() {
        animal.ganarHambre();
    }
}
