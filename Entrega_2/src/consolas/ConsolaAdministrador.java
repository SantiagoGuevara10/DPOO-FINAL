package consolas;

import galeria.usuarios.*;
import galeria.pieza.Pieza;
import galeria.inventarios.InventarioGeneral;
import subasta.Oferta;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

public class ConsolaAdministrador extends ConsolaBasica {
    private Administrador administrador;
    private InventarioGeneral inventario;
    private UsuariosRegistrados usuariosRegistrados;

    public ConsolaAdministrador(InventarioGeneral inventario, UsuariosRegistrados usuariosRegistrados) {
        this.inventario = inventario;
        this.usuariosRegistrados = usuariosRegistrados;
    }
    
    protected void mostrarMenuPrincipal() throws IOException {
        System.out.println("Bienvenido a la consola de Administración, ");
        mostrarOpcionesAdministrativas();
    }

    private void mostrarOpcionesAdministrativas() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nOpciones Administrativas:");
            System.out.println("1. Agregar Pieza");
            System.out.println("2. Devolver Pieza");
            System.out.println("3. Verificar Usuario");
            System.out.println("4. Registrar Oferta");
            System.out.println("5. Ver Historia de Compras");
            System.out.println("6. Calcular Valor de Colección");
            System.out.println("7. Mostrar el historial de una pieza");
            System.out.println("8. Salir");

            int opcion = pedirEnteroAlUsuario("Seleccione una opción:");
            switch (opcion) {
                case 1:
                    agregarPieza();
                    break;
                case 2:
                    devolverPieza();
                    break;
                case 3:
                    verificarUsuario();
                    break;
                case 4:
                    registrarOferta();
                    break;
                case 5:
                    verHistoriaCompras();
                    break;
                case 6:
                    calcularValorColeccion();
                    break;
                case 7:
                	mostrarHistorialPieza();
                case 8:
                    System.out.println("Saliendo al menú principal...");
                    continuar = false; 
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        }
    }

    private void agregarPieza() {
        String idPieza = pedirCadenaAlUsuario("Ingrese el ID de la pieza:");
        String idPropietario = pedirCadenaAlUsuario("Ingrese el ID del propietario:");
        CompradorPropietario propietario = this.buscarCompradorPorId(idPropietario);
        Pieza nuevaPieza = new Pieza(idPieza, "Default Title", 2020, "Unknown", "bodega", false, false, new LinkedList<>(), 0.0, 0, 0, new Date(), true, "No description");
        inventario.addInventarioBodega(idPieza, nuevaPieza);
        if (propietario != null) {
        	propietario.agregarPieza(nuevaPieza);
        }
        System.out.println("Pieza con ID " + idPieza + " agregada exitosamente al inventario.");
    }

    private void devolverPieza() {
        String idPieza = pedirCadenaAlUsuario("Ingrese el ID de la pieza:");
        Pieza pieza = inventario.getPiezaInventarioBodega(idPieza);
        if (pieza == null) pieza = inventario.getPiezaInventarioExhibido(idPieza);
        if (pieza == null) pieza = inventario.getPiezaInventarioPasado(idPieza);
        System.out.println("Pieza devuelta y actualizada en el inventario.");
    }

    private void verificarUsuario() {
        String idUsuario = pedirCadenaAlUsuario("Ingrese el ID del usuario a verificar:");
        
        CompradorPropietario usuario = buscarCompradorPorId(idUsuario); 
        administrador.verificarUsuario(usuario);
        System.out.println("Usuario verificado correctamente.");
    }

    private void registrarOferta() {
        String idPieza = pedirCadenaAlUsuario("Ingrese el ID de la pieza:");
        Pieza pieza = inventario.getPiezaInventarioBodega(idPieza); 
        if (pieza == null) pieza = inventario.getPiezaInventarioExhibido(idPieza);
        CompradorPropietario comprador = buscarCompradorPorId(pedirCadenaAlUsuario("Ingrese el ID del comprador:"));
        int dinero = pedirEnteroAlUsuario("Ingrese el monto de la oferta:");
        Oferta oferta = new Oferta(comprador, pieza, dinero);
        System.out.println("Oferta registrada exitosamente.");
    }
    private void verHistoriaCompras() {
        String idUsuario = pedirCadenaAlUsuario("Ingrese el ID del comprador:");
        CompradorPropietario comprador = buscarCompradorPorId(idUsuario);
        String historial = administrador.verHistoriaCompras(comprador);
        System.out.println("Historia de Compras:\n" + historial);
    }

    private void calcularValorColeccion() {
        String idUsuario = pedirCadenaAlUsuario("Ingrese el ID del comprador:");
        CompradorPropietario comprador = buscarCompradorPorId(idUsuario);
        double valorTotal = administrador.calcularValorColeccion(comprador);
        System.out.println("El valor total de la colección del comprador es: $" + valorTotal);
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
    	String role = "Administrador";
    	Administrador admin = new Administrador(idEmpleado, nombre, username, passwordHash, role);
    	users.addUsuario(admin);
    	
            
    	
    }

    
    private CompradorPropietario buscarCompradorPorId(String id) {
        for (CompradorPropietario comprador : usuariosRegistrados.getCompradoresEnPrograma()) {
            if (comprador.getIdUsuario().equals(id)) {
                return comprador;
            }
        }
        System.out.println("Comprador no encontrado.");
        return null;
    }
    
    public void mostrarHistorialPieza(){
    	Map<String, Pieza> piezas = this.inventario.getInventarioExhibido();
    	for (Pieza pieza : piezas.values()) {
    		System.out.println(pieza.getIdPieza()+"."+pieza.getTitulo());
    	}
    	String pieza_de_interes = pedirCadenaAlUsuario("Ingrese el ID de la obra que le interesa:");
    	Pieza pieza = piezas.get(pieza_de_interes);
    	
    	pieza.getHistorialPropietarios().forEach(x -> {
    		System.out.println(x.getNombre());
    	});
    	
    	pieza.getHistorialVentas().forEach(x -> {
    		System.out.println("Fue comprada por " + x.getComprador() + " Por " + x.getDinero());
    	});
    }
}
