package consolas;

import galeria.inventarios.InventarioGeneral;
import galeria.pieza.Pieza;
import galeria.usuarios.Administrador;
import galeria.usuarios.CompradorPropietario;
import galeria.usuarios.FileUtils;
import galeria.usuarios.UsuariosRegistrados;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ConsolaCompradorPropietario extends ConsolaBasica {
    private CompradorPropietario compradorPropietario;
    private InventarioGeneral inventario;

    public ConsolaCompradorPropietario(InventarioGeneral inventario, CompradorPropietario usuario) {
        this.inventario = inventario;
        this.compradorPropietario = usuario;
    }
    public ConsolaCompradorPropietario(InventarioGeneral inventario) {
        this.inventario = inventario;
    }

    protected void mostrarMenuPrincipal() throws IOException  {
        System.out.println("Bienvenido a la consola de cliente, ");
        mostrarOpcionesCompradorPropietario();
    }

    public void mostrarOpcionesCompradorPropietario() throws IOException {
    	boolean continuar = true;
        while (continuar) {
        System.out.println("Opciones de Comprador Propietario:");
        System.out.println("1. Ver piezas disponibles");
        System.out.println("2. Ver mis piezas");
        System.out.println("3. Realizar compra");
        System.out.println("4. Mostrar Historial de una pieza");
        System.out.println("5. Salir");
        int opcion = pedirEnteroAlUsuario("Seleccione una opción:");
        switch (opcion) {
            case 1:
                verPiezasDisponibles();
                break;
            case 2:
                verMisPiezas();
                break;
            case 3:
                realizarCompra();
                break;
            case 4:
            	mostrarHistorialPieza();
            	break;
            case 5:
            	System.out.println("Saliendo al menú principal...");
                continuar = false; 
                break;
            default:
            	System.out.println("Opción no válida, intente de nuevo.");
                break;
        }
      }
    }

    private void verPiezasDisponibles() {
        Map<String, Pieza> piezasDisponibles = inventario.getPiezasDisponibles();
        if (piezasDisponibles.isEmpty()) {
            System.out.println("No hay piezas disponibles en este momento.");
        } else {
            System.out.println("Piezas Disponibles:");
            for (Pieza pieza : piezasDisponibles.values()) {
                System.out.println(pieza.toString()); 
            }
        }
    }

    private void verMisPiezas() {
        if (compradorPropietario.getPiezas() == null || compradorPropietario.getPiezas().isEmpty()) {
            System.out.println("No tienes ninguna pieza en tu colección.");
        } else {
            System.out.println("Tus piezas:");
            for (Pieza pieza : compradorPropietario.getPiezas()) {
                System.out.println(pieza); 
            }
        }
    }

    private void realizarCompra() {
        String idPieza = pedirCadenaAlUsuario("Ingrese el ID de la pieza que desea comprar:");
        Pieza pieza = inventario.getPiezaInventarioBodega(idPieza);
        if (pieza != null && pieza.getDisponibleVenta()) {
            double precio = pieza.getValorFijo();  
            if (compradorPropietario.getDinero() >= precio) {
                compradorPropietario.setDinero(compradorPropietario.getDinero() - precio);
                compradorPropietario.getPiezas().add(pieza);
                inventario.removeInventarioBodega(pieza.getIdPieza());  
                System.out.println("Compra realizada exitosamente. Pieza agregada a tu colección.");
            } else {
                System.out.println("No tienes suficiente dinero para realizar esta compra.");
            }
        } else {
            System.out.println("Pieza no disponible para la venta o no existe.");
        }
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
    	String nombre = pedirCadenaAlUsuario("Ingrese su nombre completo ");
    	String username = pedirCadenaAlUsuario("Ingrese un usuario de su preferencia");
    	String passwordHash = pedirCadenaAlUsuario("Ingrese una clave de su preferencia");
    	String info = pedirCadenaAlUsuario("Ingrese su numero celular");
		int numeroo = pedirEnteroAlUsuario("Ingrese el monto que le gustaría tener para hacer compras");
		double dinero = (double)numeroo;
    	List<Pieza> piezasfav = new LinkedList<>();
    	List<Pieza> piezassss = new LinkedList<>();
    	
		
		CompradorPropietario compraaa = new CompradorPropietario(idEmpleado, nombre, username, passwordHash, info, dinero, false,piezassss ,piezasfav);
    	users.addComprador(compraaa);
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
