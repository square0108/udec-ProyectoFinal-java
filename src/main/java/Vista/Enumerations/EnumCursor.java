package Vista.Enumerations;

public enum EnumCursor {
    DEFAULT("cursor_default.png"),
    ANADIR("cursor_anadir.png");
    String image_path;
    EnumCursor(String path){
        image_path = path;
    }

    public String getImagePath(){
        return image_path;
    }
}
