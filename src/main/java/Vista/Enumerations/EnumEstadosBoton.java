package Vista.Enumerations;

public enum EnumEstadosBoton {
    DEFAULT(0),
    HOVER(1),
    CLICK(2);
    int state;
    EnumEstadosBoton(int i){
        this.state = state ;
    }
    public int getInt(){
        return state;
    }

}
