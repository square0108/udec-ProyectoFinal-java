package Model.Enumerations;

public enum BotonesEnum {
    FLECHAIZQUIERDA("flechaizquierda.png"),
    FLECHADERECHA("flechaderecha.png"),
    AGREGARITEM("agregaritem.png");
    private String direccionImagen;
    BotonesEnum(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
    public String getDireccionImagen() {
        return direccionImagen;
    }
}
