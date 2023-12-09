package Vista.Enumerations;

/**
 * Enumerador el cual sirve para que en BotonFlecha, se pueda elegir la dirección del boton.
 */
public enum BotonesEnum {
    FLECHAIZQUIERDA("flechaizquierda.png"),
    FLECHADERECHA("flechaderecha.png");
    private String direccionImagen;
    BotonesEnum(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
    /**
     * Devuelve la ruta de la imagen de cada caso de botón.
     * @return nombre del archivo que contiene la textura.
     */
    public String getDireccionImagen() {
        return direccionImagen;
    }
}
