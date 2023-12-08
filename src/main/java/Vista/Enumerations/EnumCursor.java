package Vista.Enumerations;

public enum EnumCursor {
    DEFAULT("cursor_default.png"),
    ANADIR_ANIMAL("cursor_anadir_animal.png"),
    ANADIR_COMIDA("cursor_anadir_comida.png"),
    ANADIR_HABITAT("cursor_anadir_habitat.png");
    String image_path;
    EnumCursor(String path){
        image_path = path;
    }

    public String getImagePath(){
        return image_path;
    }
}
