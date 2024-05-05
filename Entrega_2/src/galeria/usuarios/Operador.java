package galeria.usuarios;

import java.util.List;
import subasta.Subasta;

public class Operador extends Empleado {
    private Subasta subastaActual;
    private List<Subasta> subastasRealizadas;

    public Operador(String idEmpleado, String nombre, String username, String passwordHash, String role) {
        super(idEmpleado, nombre, username, passwordHash, role);
    }

    

    public void asignarSubasta(Subasta subasta) {
        this.subastasRealizadas.add(subasta);
        this.subastaActual = subasta;
    }


    public void coordinarEventos() {
        
       
    }

  
    public void programarEventos() {
        
        
    }

    public Comprador hallarComprador(String compradorId) {
        List<Comprador> compradores = this.subastaActual.getCompradores();
        for (Comprador comprador : compradores) {
            if (comprador.getIdUsuario().equals(compradorId)) {
                return comprador;
            }
        }
        return null;
    }

    public void mandarOferta(String piezaId) {
        this.subastaActual.mandarOferta(piezaId);
    }
}