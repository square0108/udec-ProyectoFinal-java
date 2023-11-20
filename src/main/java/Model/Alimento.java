package Model;

public class Alimento {
    private boolean consumidoStatus = false;
    public Alimento() {};
    public boolean estaConsumido() {return this.consumidoStatus;}
    public void setConsumidoStatus(boolean b) {this.consumidoStatus = b;}
}
