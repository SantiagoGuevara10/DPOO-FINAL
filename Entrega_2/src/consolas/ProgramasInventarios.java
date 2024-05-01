package consolas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import galeria.inventarios.InventarioGeneral;
import galeria.inventarios.PiezaFotografia;
import galeria.pieza.Pieza;
import galeria.usuarios.Propietario;

/**
 * Clase que permite probar el hecho de accionar el inventario, es decir manipular cosas del mismo.
 */
public class ProgramasInventarios {
	Propietario propietario = new Propietario("1234", new ArrayList<>());
	
	public ProgramasInventarios() {
		// Se crea una pieza
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
        
        
	}
	
	public static void main(String[] args) {
		new ProgramasInventarios();
	}
	

}
