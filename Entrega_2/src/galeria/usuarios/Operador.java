package galeria.usuarios;

import java.util.List;
import subasta.Subasta;

public class Operador extends Empleado {
    private Subasta subastaActual;
    private List<Subasta> subastasRealizadas;

    public Operador(String idEmpleado, String nombre, String username, String passwordHash, String role) {
        super(idEmpleado, nombre, username, passwordHash, role);
    }
    
   
}