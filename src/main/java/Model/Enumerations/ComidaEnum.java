package Model.Enumerations;

public enum ComidaEnum {
    CARNE("carne.png"),
    HENO("heno.png");
    String texturePath;

    ComidaEnum(String texturePath) {
        this.texturePath = texturePath;
    }
    public String getTexturePath() {
        return texturePath;
    }
    public ComidaEnum siguiente() {
        int siguienteIndice = (this.ordinal() + 1) % ComidaEnum.values().length;
        return ComidaEnum.values()[siguienteIndice];
    }

    public ComidaEnum anterior() {
        int anteriorIndice = (this.ordinal() - 1 + ComidaEnum.values().length) % ComidaEnum.values().length;
        return ComidaEnum.values()[anteriorIndice];
    }
}
