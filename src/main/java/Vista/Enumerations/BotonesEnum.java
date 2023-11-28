package Vista.Enumerations;

public enum BotonesEnum {
    FLECHAIZQUIERDA("flechaizquierda.png"),
    FLECHADERECHA("flechaderecha.png");
    private String direccionImagen;
    BotonesEnum(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
    public String getDireccionImagen() {
        return direccionImagen;
    }
}
