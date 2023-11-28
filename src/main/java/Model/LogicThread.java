package Model;

import Controller.ZooController;


public class LogicThread implements Runnable {
    protected ZooController controller;

    public LogicThread(ZooController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while(true) {
            controller.removerTodosLosMuertos();
            controller.actualizarHambreTodos();
            try {
                Thread.sleep(ZooController.LOGIC_TICKRATE_MS);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
