package galeria.usuarios;

import galeria.pieza.Pieza;
import java.util.LinkedList;
import java.util.List;
import subasta.Oferta;
import subasta.Subasta;
import galeria.inventarios.*;

public class Administrador extends Empleado {
	
	private List<Oferta> ofertas;
	
	
	
    public Administrador(String idEmpleado, String nombre, String username, String passwordHash, String role, List<String> ofertas) {
        super(idEmpleado, nombre, username, passwordHash, role);
        ofertas = new LinkedList<>();
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
		     propietario.setPiezas(pieza);
			
		}
		else if (pieza.getEstadoPieza() == "exhibida") {
			inventario.removeInventarioExhibido(pieza.getIdPieza());
			inventario.addInventarioPasado(pieza.getIdPieza(), pieza);
			propietario.setPiezas(pieza);
			
			
		}
		
	}

	public void verificarUsuario(CompradorPropietario usuario) {
		usuario.setEstaVerificado(true);
	}

	

	public void agregarOferta(Oferta oferta){
		ofertas.add(oferta);
	}
	
		
	}