package galeria.usuarios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cajero extends Empleado {
    private List<String> transacciones = new ArrayList<>();

    public Cajero(String idEmpleado, String nombre, String username, String passwordHash, String role) {
        super(idEmpleado, nombre, username, passwordHash, role);
    }

    

    public void procesarTransacciones() {
     
        transacciones.add("Transacción " + System.currentTimeMillis());
      
       
    }

    public void emitirRecibos() {
        
        transacciones.forEach(transaccion -> {
            try (FileWriter writer = new FileWriter("Recibo_" + transaccion.hashCode() + ".txt")) {
                writer.write("Recibo de la " + transaccion);
               
            } catch (IOException e) {
                
            }
        });
      
        transacciones.clear();
    }
}