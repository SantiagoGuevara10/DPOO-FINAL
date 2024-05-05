package galeria.usuarios;

import java.util.HashMap;
import java.util.Map;

public class Operador extends Empleado {
    private Map<String, Double> ofertasRegistradas; 

    public Operador(String idEmpleado, String nombre, String username, String passwordHash, String role) {
        super(idEmpleado, nombre, username, passwordHash, role);
        ofertasRegistradas = new HashMap<>();
    }

    
    public void registrarOferta(String idPieza, double montoOferta) {
        ofertasRegistradas.put(idPieza, montoOferta);
        
    }

    
    public Map<String, Double> getOfertasRegistradas() {
        return ofertasRegistradas;
    }
}