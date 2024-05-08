package galeria.usuarios;



import galeria.pieza.Pieza;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cajero extends Empleado {
    private List<String> transacciones;

    public Cajero(String idEmpleado, String nombre, String username, String passwordHash, String role) {
        super(idEmpleado, nombre, username, passwordHash, role);
		transacciones = new LinkedList<>();

        
        
    }

    public List<String>  getTransacciones(){
    	return transacciones;
    }
    
    public void setTransacciones(String transaccion) {
    	this.transacciones.add(transaccion);
    }
    
    public void procesarPago(CompradorPropietario comprador, CompradorPropietario vendedor, double monto, Pieza pieza) {
        
        if (comprador.getDinero() >= monto) {
            
            comprador.setDinero(comprador.getDinero() - monto);
      
            vendedor.setDinero(vendedor.getDinero() + monto);
     
            transacciones.add("Pago de $" + monto + " realizado por " + comprador.getNombre() + " para la pieza " + pieza.getTitulo());
            
        }
    }
}

 
    