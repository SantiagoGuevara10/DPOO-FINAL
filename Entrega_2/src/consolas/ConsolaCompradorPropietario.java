package consolas;

import galeria.inventarios.InventarioGeneral;
import galeria.inventarios.PiezaEscultura;
import galeria.inventarios.PiezaFotografia;
import galeria.inventarios.PiezaPintura;
import galeria.inventarios.PiezaVideo;
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
    private CompradorPropietario compradordelPrograma;
    private InventarioGeneral inventario;
    private UsuariosRegistrados users;

    public ConsolaCompradorPropietario(InventarioGeneral inventario,UsuariosRegistrados users) {
        this.inventario = inventario;
        this.users = users;
    }

    protected void mostrarMenuPrincipal() throws IOException  {
        System.out.println("Bienvenido a la consola de cliente, ");
        mostrarOpcionesCompradorPropietario();
    }

    public void mostrarOpcionesCompradorPropietario() throws IOException {
    	boolean continuar = true;
        while (continuar) {
        System.out.println("Opciones de Comprador Propietario:");
        System.out.println("1. Ver piezas disponibles para compras");
        System.out.println("2. Ver mis piezas");
        System.out.println("3. Realizar compra");
        System.out.println("4. Registrar pieza que tiene");
        System.out.println("5. Pedir verificación al Administrador");
        System.out.println("6. Salir");
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
            	registrarPieza(users, compradordelPrograma);
                break;
            case 5:
                
                break;
            
            case 6:
            	System.out.println("Saliendo al menú principal...");
                continuar = false; 
                break;
            
            default:
            	System.out.println("Opción no válida, intente de nuevo.");
                break;
        }
      }
    }

    private void registrarPieza(UsuariosRegistrados users, CompradorPropietario comprador) {
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
     	String idPieza = String.valueOf(numeroAleatorio);
    	String titulo = pedirCadenaAlUsuario("Ingrese el titulo de la pieza");
    	int anioCreacion = pedirEnteroAlUsuario("Ingrese el año de la creación de la pieza");
    	String lugarCreacion = pedirCadenaAlUsuario("Ingrese el lugar donde la pieza fue creada");
    	String estadoPieza  = pedirCadenaAlUsuario("Condiciones en las que se encuentra la pieza");
    	boolean estaExhibida = false;
    	boolean disponibleVenta = false;
    	List<String> autores = new LinkedList<>();
    	String autoresString = pedirCadenaAlUsuario("Ingrese los autores de la obra y separe por , cada uno");
    	if(autoresString.contains(",")) {
    	String[] partes = autoresString.split( "," );
    	for(int k=0; k<partes.length;k++) {
    		autores.add(partes[k]);
    	}
    	}else {autores.add(autoresString);}
    	double valorFijo = 0;
    	int valorMinimo = 0;
    	int valorInicial = 0;
    	Date fecha = new Date();
    	boolean esVigente = true;
    	System.out.println("Ingrese el tipo de pieza: 1.Escultura  2.Fotografia  3.Pintura  4.Video");
    	int opcion = pedirEnteroAlUsuario("Seleccione una opción:");
    	String descripcion= "";
    	Pieza pieza = null;
    	int peso = 0;
        switch (opcion) {
            case 1:
            	descripcion = "Escultura";
            	peso = pedirEnteroAlUsuario("Indique el peso de la pieza");
                boolean usaElectricidad = pedirConfirmacionAlUsuario("La escultura usa electricidad");
                pieza = new PiezaEscultura(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, peso, usaElectricidad);
                
            case 2:
            	descripcion = "Fotografia";
            	boolean esDigital = pedirConfirmacionAlUsuario("La fotografia es digital");
                pieza = new PiezaFotografia(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, esDigital);

                
            case 3:
            	descripcion = "Pintura";
            	peso = pedirEnteroAlUsuario("Indique el peso de la pieza");
                String tecnica = pedirCadenaAlUsuario("Tecnica que tiene la pintura");
                pieza = new PiezaPintura(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, peso, tecnica);
                
            case 4:
            	descripcion = "Video";	
            	String calidad = pedirCadenaAlUsuario("Ingrese la calidad del video");
	            int duracion = pedirEnteroAlUsuario("Ingrese la duracion del video en minutos");
	                pieza = new PiezaVideo(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, calidad, duracion);

            	}
        
        comprador.agregarPieza(pieza);
    	
        
		
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
    	
    	compradordelPrograma = compraaa;
}}
