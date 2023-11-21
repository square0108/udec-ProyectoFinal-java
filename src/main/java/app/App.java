package app;

import Vista.HebraVisual;

public class App {
    public static void main(String[] args) {
        HebraVisual hebraVisual = new HebraVisual();
        new Thread(hebraVisual).start();
    }
}