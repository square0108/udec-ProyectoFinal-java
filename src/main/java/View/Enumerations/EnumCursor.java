package View.Enumerations;

/**
 * Enumerador usado para elegir el modo del Mouse. Además es usado para saber la ruta de la textura.
 */
public enum EnumCursor {
    DEFAULT("cursor_default.png"),
    ANADIR_ANIMAL("cursor_anadir_animal.png"),
    ANADIR_COMIDA("cursor_anadir_comida.png"),
    ANADIR_HABITAT("cursor_anadir_habitat.png");
    String image_path;
    EnumCursor(String path){
        image_path = path;
    }

    /**
     * Devuelve la ruta de la textura.
     * @return nombre del archivo que contiene la textura.
     */
    public String getImagePath(){
        return image_path;
    }
}
