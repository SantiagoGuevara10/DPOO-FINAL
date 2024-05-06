package consolas;

import galeria.inventarios.InventarioGeneral;
import galeria.pieza.Pieza;
import galeria.usuarios.CompradorPropietario;
import galeria.usuarios.FileUtils;

import java.io.IOException;
import java.util.Map;

public class ConsolaCompradorPropietario extends ConsolaBasica {
    private CompradorPropietario compradorPropietario;
    private InventarioGeneral inventario;

    public ConsolaCompradorPropietario(InventarioGeneral inventario) {
        this.inventario = inventario;
    }

    protected void mostrarMenuPrincipal() throws IOException {
        System.out.println("Bienvenido a la consola del Comprador Propietario.");
        if (compradorPropietario == null) {
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
            mostrarOpcionesCompradorPropietario();
        }
    }

    private void iniciarSesion() throws IOException {
        String username = pedirCadenaAlUsuario("Ingrese su nombre de usuario:");
        String password = pedirCadenaAlUsuario("Ingrese su contraseña:");

        if (FileUtils.verifyUser(username, password) && "comprador_propietario".equals(FileUtils.getRole(username))) {
            this.compradorPropietario = new CompradorPropietario(username, "Comprador", username, password, "comprador_propietario", 0.0, true, null, null);
            System.out.println("Inicio de sesión exitoso.");
            mostrarOpcionesCompradorPropietario();
        } else {
            System.out.println("Inicio de sesión fallido. Intente nuevamente.");
            mostrarMenuPrincipal();
        }
    }

    private void registrarNuevoUsuario() throws IOException {
        String username = pedirCadenaAlUsuario("Ingrese un nombre de usuario:");
        String password = pedirCadenaAlUsuario("Ingrese una contraseña:");

        FileUtils.registerUser(username, password, "comprador_propietario");
        System.out.println("Usuario registrado exitosamente como comprador propietario. Por favor inicie sesión.");
        mostrarMenuPrincipal();
    }

    private void mostrarOpcionesCompradorPropietario() throws IOException {
        System.out.println("Opciones de Comprador Propietario:");
        System.out.println("1. Ver piezas disponibles");
        System.out.println("2. Ver mis piezas");
        System.out.println("3. Realizar compra");
        System.out.println("4. Salir");
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
                System.out.println("Saliendo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida, intente de nuevo.");
                mostrarOpcionesCompradorPropietario();
                break;
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
        if (pieza != null && pieza.isDisponibleVenta()) {
            double precio = pieza.getValorFijo();  
            if (compradorPropietario.getDinero() >= precio) {
                compradorPropietario.setDinero(compradorPropietario.getDinero() - precio);
                pieza.setPropietario(compradorPropietario);
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
}
