package Model;

public class Alimento {
    private DietaEnum tipoAlimento;
    public Alimento(DietaEnum d) {
        this.tipoAlimento = d;
    }
    public DietaEnum verificarTipoAlimento() {
        return tipoAlimento;
    }
}
