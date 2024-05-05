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
		     propietario.setPiezas(null);
			
		}
		else if (pieza.getEstadoPieza() == "exhibida") {
		}
		
	}

	public void verificarUsuario(CompradorPropietario usuario) {
		usuario.setEstaVerificado(true);
	}
	
	piblic void 
		
	}