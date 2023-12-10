package View.Interface;

/**
 * Interfaz semejante a un "Subscriber" de un patrón Observer. Se subscribe a sus subpaneles (PanelSeleccion*) para escuchar cuando actualizar el cursor.
 */
public interface ParentPanel {
    void contextualUpdate(SubPanel subPanel);
}
