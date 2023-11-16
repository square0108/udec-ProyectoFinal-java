package Model;

import org.jetbrains.annotations.NotNull;

public abstract class Carnivoro implements Animal {
    private int porcentajeComida;

    public Carnivoro() {
        this.porcentajeComida = 100;
    }
    public void comer(@NotNull Alimento alimento) {
        /* TODO: manejar Alimento NULL */
        if (alimento.verificarTipoAlimento() == DietaEnum.CARNIVORO) {
            porcentajeComida = 100;
        }
        else {System.out.println("Tipo de alimento equivocado: " + alimento.toString() + ", Animal: " + this.toString());}
        /* posiblemente, variar la restauracion de comida segun la nutricion del alimento */

    }
    public abstract void hambreUp(); /* especifico por cada animal */
    public abstract TerrenoEnum cualTerreno();
    public int getPorcentajeComida() {return this.porcentajeComida;}
    public void restarPorcentajeComida(int tasaHambre) {this.porcentajeComida = porcentajeComida - tasaHambre;}
}
