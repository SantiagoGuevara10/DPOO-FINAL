package consolas;

import galeria.inventarios.InventarioGeneral;
import galeria.pieza.Pieza;
import galeria.usuarios.CompradorPropietario;
import galeria.usuarios.Cajero;
import galeria.usuarios.FileUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class ConsolaCajero extends ConsolaBasica {
    private Cajero cajero;
    private InventarioGeneral inventario;
    private List<String> transacciones = new ArrayList<>();

    public ConsolaCajero(InventarioGeneral inventario) {
        this.inventario = inventario;
    }

    protected void mostrarMenuPrincipal() throws IOException {
        System.out.println("Bienvenido a la consola del Cajero.");
        if (cajero == null) {
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            int opcion = pedirEnteroAlUsuario("Seleccione una opción:");
            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registrarNuevoUsuario();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    mostrarMenuPrincipal();
                    break;
            }
        } else {
            mostrarOpcionesDeCajero();
        }
    }

    private void iniciarSesion() throws IOException {
        String username = pedirCadenaAlUsuario("Ingrese su nombre de usuario:");
        String password = pedirCadenaAlUsuario("Ingrese su contraseña:");

        if (FileUtils.verifyUser(username, password) && "cajero".equals(FileUtils.getRole(username))) {
            this.cajero = new Cajero(username, "Cajero", username, password, "cajero");
            System.out.println("Inicio de sesión exitoso.");
            mostrarOpcionesDeCajero();
        } else {
            System.out.println("Inicio de sesión fallido. Intente nuevamente.");
            mostrarMenuPrincipal();
        }
    }

    private void registrarNuevoUsuario() throws IOException {
        String username = pedirCadenaAlUsuario("Ingrese un nombre de usuario:");
        String password = pedirCadenaAlUsuario("Ingrese una contraseña:");

        FileUtils.registerUser(username, password, "cajero");
        System.out.println("Usuario registrado exitosamente como cajero. Por favor inicie sesión.");
        mostrarMenuPrincipal();
    }

    private void mostrarOpcionesDeCajero() {
        System.out.println("Opciones de Cajero:");
        System.out.println("1. Procesar Pago");
        System.out.println("2. Emitir Recibos");
        System.out.println("3. Salir");
        int opcion = pedirEnteroAlUsuario("Seleccione una opción:");
        switch (opcion) {
            case 1:
                procesarPago();
                break;
            case 2:
                emitirRecibos();
                break;
            case 3:
                System.out.println("Saliendo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida, intente de nuevo.");
                mostrarOpcionesDeCajero();
                break;
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
    
}
