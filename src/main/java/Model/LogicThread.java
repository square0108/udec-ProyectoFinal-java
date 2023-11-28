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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
