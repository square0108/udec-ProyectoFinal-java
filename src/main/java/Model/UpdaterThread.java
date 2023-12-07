package Model;

import Controller.ZooController;


public class UpdaterThread implements Runnable {
    protected ZooController controller;

    public UpdaterThread(ZooController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while(true) {
            controller.updateHabitatsModelView();
            try {
                Thread.sleep(controller.FRAMETIME_MS);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
