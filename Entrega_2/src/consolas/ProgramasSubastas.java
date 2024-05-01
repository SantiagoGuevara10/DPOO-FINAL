package consolas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.inventarios.InventarioGeneral;
import galeria.inventarios.PiezaFotografia;
import galeria.usuarios.Comprador;
import galeria.usuarios.Propietario;
import subasta.Oferta;
import subasta.Subasta;
import galeria.pieza.Pieza;

/**
 * Clase que permite probar el hecho de iniciar una subasta, y la oferta y todo lo que puede realizar.
 */
public class ProgramasSubastas {
	
	
	public ProgramasSubastas() {
		Propietario propietario = new Propietario("1234", new ArrayList<>());
		Pieza pieza = new Pieza( "P002", "Sol de Medianoche", 2020,"España", "Exhibicion",true,false,Arrays.asList("Elena Ochoa"),25000.0,20000,15000,new Date(),true,"Una hermosa obra de arte moderno.",propietario);
		Propietario propietario2 = new Propietario("2143", new ArrayList<>());
		PiezaFotografia pieza2 = new PiezaFotografia("P003","La Mona Lisa", 1506, "Louvre",
                           "Exhibicion", true,false,
                           Arrays.asList("da Vinci"), 2400000, 700000,500000,
                           new Date(), true, "Considered an archetypal masterpiece of the Italian Renaissance", propietario2,
                           false);
        System.out.println(pieza);
        // Se crea el inventario
        InventarioGeneral inventario = new InventarioGeneral();
        // Se agrega una pieza al inventario en exhibición
        inventario.addInventarioExhibido(pieza.getIdPieza(), pieza);
        inventario.addInventarioExhibido("P003", pieza2);
        // Se extrae el mapa que contiene el inventario exhibido
        Map<String,Pieza> mapa = inventario.getInventarioExhibido();
        System.out.println(mapa);
        // Se extraen cosas del mapa
        inventario.removeInventarioExhibido("P002");
        inventario.addInventarioPasado("P002", pieza);
        System.out.println(mapa);
        System.out.println(inventario.getInventarioPasado());
        
		
		// Se crean compradores dispuestos a ofertar
		Comprador comprador = new Comprador("P001", "Juan Murillo", "juanjomurillo12", "3155027560", 1000000, true);
		Comprador comprador2 = new Comprador("P004", "Abraham Gomez", "ab.gomez", "3155022143", 750000, true);
		
		
		
		
		LinkedList<Pieza> piezas_Disponibles = new LinkedList<>();
		LinkedList<Comprador> compradores_Disponibles = new LinkedList<>();
		
		
		// Se añaden a una lista todas las piezas disponibles
		for(Map.Entry<String, Pieza> entry : mapa.entrySet()) {
			Pieza valor = entry.getValue();
			piezas_Disponibles.add(valor);
			
			
	    // Se añaden compradores a una lista
		compradores_Disponibles.add(comprador2);
		compradores_Disponibles.add(comprador);
	
		// Se crea la subasta
		Subasta subasta = new Subasta(piezas_Disponibles, compradores_Disponibles);
		
		//Se muestran las piezas disponibles
		System.out.println("Las disponibles son");
		System.out.println(subasta.getPiezasDisponibles());
		
		// Los compradores ofertan por la pieza
		subasta.crearOferta(comprador2, "P003", 550000);
		subasta.crearOferta(comprador, "P003", 600000);

		//Se manda la oferta al administrador
		subasta.mandarOferta("P003");
		
		//Se retorna la máxima oferta
		Oferta oferta = subasta.getMaximaOferta("P003");
		
		//Se imprime al comprador con la máxima oferta
		System.out.println(oferta);
		Comprador compradorganador = oferta.getComprador();
		System.out.println(compradorganador);
		//Nombre del comprador ganador
		System.out.println(compradorganador.getNombre());
		//Se entrega la pieza al comprador y se le cobra
		Pieza piezafinal = inventario.getPiezaInventarioExhibido("P003");
		inventario.removeInventarioExhibido("P003");
		int pago = oferta.getDinero();
		double dinero = compradorganador.getLimiteCompra();
		double total = dinero - pago;
		compradorganador.setLimiteCompra(total);
		System.out.println(total);
		compradorganador.addPieza(piezafinal);
		

		
		}
		
		
		
		
		
		
		

		
		
		
	}
	
	public static void main(String[] args) {
		new ProgramasSubastas();
	}

}
