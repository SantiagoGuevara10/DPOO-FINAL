package galeria.usuarios;

import galeria.pieza.Pieza;
import java.util.LinkedList;
import java.util.List;
import subasta.Oferta;
import subasta.Subasta;
import galeria.inventarios.*;

public class Administrador extends Empleado {
	
	
	
    public Administrador(String idEmpleado, String nombre, String username, String passwordHash, String role) {
        super(idEmpleado, nombre, username, passwordHash, role);
        
    }

	public void agregarPieza(Pieza pieza, InventarioGeneral inventario) {
		if (pieza.getEstadoPieza() == "bodega") {
			inventario.addInventarioBodega(pieza.getIdPieza(),pieza);
		}
		else if (pieza.getEstadoPieza() == "exhibida") {
			inventario.addInventarioExhibido(pieza.getIdPieza(),pieza);
		}
		else if (pieza.getEstadoPieza() == "vendida") {
			inventario.addInventarioPasado(pieza.getIdPieza(),pieza);
		}
	}
	
	public void devolverPieza(Pieza pieza, InventarioGeneral inventario, CompradorPropietario propietario) {
		if (pieza.getEstadoPieza() == "bodega") {
		     inventario.removeInventarioBodega(pieza.getIdPieza());
		     inventario.addInventarioPasado(pieza.getIdPieza(), pieza);
		     propietario.setPieza(pieza);
			
		}
		else if (pieza.getEstadoPieza() == "exhibida") {
			inventario.removeInventarioExhibido(pieza.getIdPieza());
			inventario.addInventarioPasado(pieza.getIdPieza(), pieza);
			propietario.setPiezaFav(pieza);
			
			
		}
		
	}

	public void verificarUsuario(CompradorPropietario usuario) {
		usuario.setEstaVerificado(true);
	}
	
	public String verHistoriaCompras(CompradorPropietario comprador) {
	    StringBuilder historial = new StringBuilder();
	    List<Pieza> piezasCompradas = comprador.getPiezas();

	    for (Pieza pieza : piezasCompradas) {
	        String fechaIngreso = (pieza.getFechaDeIngreso() != null) ? pieza.getFechaDeIngreso().toString() : "Fecha desconocida";
	        historial.append(String.format("%s comprada el %s por $%.2f\n",
	                pieza.getTitulo(),
	                fechaIngreso,
	                pieza.getValorFijo()));
	    }
	    return historial.toString();
	}

    public double calcularValorColeccion(CompradorPropietario comprador) {
        double totalValor = 0.0;
        for (Pieza pieza : comprador.getPiezas()) {
            totalValor += pieza.getValorFijo();
        }
        return totalValor;
    }

	

	
	
		
	}