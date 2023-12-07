package Model.Enumerations;

public enum EstadosEnum {
    PASIVO(0),
    MOVIENDO(1),
    COMIENDO(2),
    MUERTO(3);
    int number;
    EstadosEnum(int n){
        this.number = n;
    }
    public int getNumber(){
        return this.number;
    }
}
