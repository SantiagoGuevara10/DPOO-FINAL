package consolas;

import galeria.inventarios.InventarioGeneral;
import galeria.pieza.Pieza;

import java.io.IOException;
import galeria.usuarios.Operador;
import galeria.usuarios.UsuariosRegistrados;
import galeria.usuarios.Administrador;
import galeria.usuarios.FileUtils;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



public class ConsolaOperador extends ConsolaBasica {
    private Operador operador;
    private InventarioGeneral inventario;
    private Map<String, Double> ofertasRegistradas;

    public ConsolaOperador(InventarioGeneral inventario) {
        this.inventario = inventario;
        this.ofertasRegistradas = new HashMap<>();
    }

    protected void mostrarMenuPrincipal() throws IOException  {
        System.out.println("Bienvenido a la consola de operador, ");
        mostrarOpcionesDeOperador();
    }

    private void mostrarOpcionesDeOperador() {
    	boolean continuar = true;
        while (continuar) {
        System.out.println("Opciones de Operador:");
        System.out.println("1. Registrar Oferta");
        System.out.println("2. Mostrar Historial de una Pieza");
        System.out.println("3. Salir");
        int opcion = pedirEnteroAlUsuario("Seleccione una opción:");
        switch (opcion) {
            case 1:
                registrarOferta();
                break;
            case 2:
            	mostrarHistorialPieza();
            	break;
            case 3:
            	System.out.println("Saliendo al menú principal...");
                continuar = false; 
                break;
            default:
            	System.out.println("Opción no válida, intente de nuevo.");
                break;
        }
      }
    }

    private void registrarOferta() {
        String idPieza = pedirCadenaAlUsuario("Ingrese el ID de la pieza:");
        double montoOferta = pedirNumeroAlUsuario("Ingrese el monto de la oferta:");
        ofertasRegistradas.put(idPieza, montoOferta);
        System.out.println("Oferta registrada exitosamente para la pieza: " + idPieza);
    }
    
    
    public void crearUsuario(UsuariosRegistrados users) {
        Random random = new Random();
        List<Integer> numeros = new LinkedList<>();
        for(int i =0; i<users.getUsuariosEnPrograma().size();i++) {
        	String num = users.getUsuariosEnPrograma().get(i).getIdEmpleado();
        	int numero = Integer.parseInt(num);
        	numeros.add(numero);
        	
        }
        
        for(int i =0; i<users.getCompradoresEnPrograma().size();i++) {
        	String num = users.getCompradoresEnPrograma().get(i).getIdUsuario();
        	int numero = Integer.parseInt(num);
        	numeros.add(numero);
        	
        }
        
        int numeroAleatorio;
        do {
            numeroAleatorio = random.nextInt(1000); // Generar un número aleatorio entre 0 y 9 (por ejemplo)
        } while (numeros.contains(numeroAleatorio));
        
    	String idEmpleado = String.valueOf(numeroAleatorio);
    	String nombre = pedirCadenaAlUsuario("Ingrese su nombre completo: ");
    	String username = pedirCadenaAlUsuario("Ingrese un usuario de su preferencia");
    	String passwordHash = pedirCadenaAlUsuario("Ingrese una clave de su preferencia");
    	String role = "Operador";
    	Operador oper = new Operador(idEmpleado, nombre, username, passwordHash, role);
    	users.addUsuario(oper);
    	
            
    	
    }
    public void mostrarHistorialPieza(){
    	Map<String, Pieza> piezas = this.inventario.getInventarioExhibido();
    	for (Pieza pieza : piezas.values()) {
    		System.out.println(pieza.getIdPieza()+"."+pieza.getTitulo());
    	}
    	String pieza_de_interes = pedirCadenaAlUsuario("Ingrese el ID de la obra que le interesa:");
    	Pieza pieza = piezas.get(pieza_de_interes);
    	
    	if (pieza != null) {
    		pieza.getHistorialPropietarios().forEach(x -> {
    			System.out.println(x.getNombre());
    		});
    	
    		pieza.getHistorialVentas().forEach(x -> {
    			System.out.println("Fue comprada por " + x.getComprador() + " Por " + x.getDinero());
    		});
    	}
    }
}