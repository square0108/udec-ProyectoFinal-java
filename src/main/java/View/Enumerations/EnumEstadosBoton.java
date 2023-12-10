package View.Enumerations;

/**
 * Enumerador usado para elegir como se muestran los botones (Default, Hover, Click).
 */
public enum EnumEstadosBoton {
    DEFAULT(0),
    HOVER(1),
    CLICK(2);
    int state;
    EnumEstadosBoton(int i){
        this.state = i ;
    }
    public int getInt(){
        return state;
    }

}