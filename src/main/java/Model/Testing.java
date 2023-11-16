package Model;

import Model.Especies.*;

public class Testing {
    public static void main(String[] args) {
        Leon manuel = new Leon();
        System.out.println(manuel instanceof Carnivoro); // use this
        // System.out.println(manuel.getClass() == Carnivoro.class); not this!!
    }
}
