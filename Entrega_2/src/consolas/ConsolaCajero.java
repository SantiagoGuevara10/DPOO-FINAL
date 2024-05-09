package consolas;

import galeria.inventarios.InventarioGeneral;
import galeria.pieza.Pieza;
import galeria.usuarios.CompradorPropietario;
import galeria.usuarios.Administrador;
import galeria.usuarios.Cajero;
import galeria.usuarios.FileUtils;
import galeria.usuarios.UsuariosRegistrados;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class ConsolaCajero extends ConsolaBasica {
    private Cajero cajero;
    private InventarioGeneral inventario;
    private List<String> transacciones = new ArrayList<>();

    public ConsolaCajero(InventarioGeneral inventario) {
        this.inventario = inventario;
    }

    protected void mostrarMenuPrincipal() throws IOException  {
        System.out.println("Bienvenido a la consola de cajero, ");
        mostrarOpcionesDeCajero();
    }
    

    private void mostrarOpcionesDeCajero() {
    	boolean continuar = true;
    	while (continuar) {
        System.out.println("Opciones de Cajero:");
        System.out.println("1. Procesar Pago");
        System.out.println("2. Emitir Recibos");
        System.out.println("3. Mostrar el Historial de una pieza");
        System.out.println("4. Salir");
        int opcion = pedirEnteroAlUsuario("Seleccione una opción:");
        switch (opcion) {
            case 1:
                procesarPago();
                break;
            case 2:
                emitirRecibos();
                break;
            case 3:
            	mostrarHistorialPieza();
            	break;
            case 4:
                System.out.println("Saliendo...");
                continuar = false; 
                break;
            default:
            	System.out.println("Opción no válida, intente de nuevo.");
                break;
        }
      }
    }

    private void procesarPago() {
        String idComprador = pedirCadenaAlUsuario("Ingrese el ID del comprador:");
        String idVendedor = pedirCadenaAlUsuario("Ingrese el ID del vendedor:");
        double monto = pedirNumeroAlUsuario("Ingrese el monto del pago:");
        String idPieza = pedirCadenaAlUsuario("Ingrese el ID de la pieza:");

        CompradorPropietario comprador = buscarCompradorPorId(idComprador);
        CompradorPropietario vendedor = buscarCompradorPorId(idVendedor);
        Map<String, Pieza> piezasDisponibles = inventario.getPiezasDisponibles();
        Pieza pieza = piezasDisponibles.get(idPieza);  

        if (comprador != null && vendedor != null && pieza != null) {
            cajero.procesarPago(comprador, vendedor, monto, pieza);
            System.out.println("Pago procesado exitosamente.");
        } else {
            System.out.println("No se pudo procesar el pago. Verifique los IDs de comprador, vendedor y pieza.");
        }
    }

    private void emitirRecibos() {
        transacciones.forEach(transaccion -> {
            try {
                emitirRecibo(transaccion);
            } catch (IOException e) {
                System.out.println("Error al emitir recibo: " + e.getMessage());
            }
        });
        transacciones.clear();  
        System.out.println("Recibos emitidos exitosamente.");
    }

    private void emitirRecibo(String transaccion) throws IOException {
        try (FileWriter writer = new FileWriter("Recibo_" + transaccion.hashCode() + ".txt")) {
            writer.write("Recibo de la transacción: " + transaccion);
        }
    }

    private CompradorPropietario buscarCompradorPorId(String id) {
        
        return new CompradorPropietario(id, "Nombre", "username", "passwordHash", "contactInfo", 0.0, true, new LinkedList<>(), new LinkedList<>());
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
    	String role = "Cajero";
        Cajero cajero = new Cajero(idEmpleado, nombre, username, passwordHash, role);
    	users.addUsuario(cajero);
    	
            
    	
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
