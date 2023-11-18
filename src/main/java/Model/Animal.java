package Model;

public abstract class Animal {
    private int porcentajeComida;
    private final int gananciaHambre;
    public Animal(int gananciaHambre) {
        this.porcentajeComida = 100;
        this.gananciaHambre = gananciaHambre;
    }
    public void comer(Alimento alimento) {
        if (alimento != null && alimento.getConsumidoStatus() == false) {
            this.porcentajeComida = 100;
            alimento.setConsumidoStatus(true);
        }
    }
    public void ganarHambre() {
        porcentajeComida = porcentajeComida - gananciaHambre;
    }


    /* TODO: void movement()
    */
}
