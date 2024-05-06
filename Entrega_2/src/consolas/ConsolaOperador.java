package consolas;

import galeria.inventarios.InventarioGeneral;
import java.io.IOException;
import galeria.usuarios.Operador;
import galeria.usuarios.FileUtils;
import java.util.Map;
import java.util.HashMap;



public class ConsolaOperador extends ConsolaBasica {
    private Operador operador;
    private InventarioGeneral inventario;
    private Map<String, Double> ofertasRegistradas;

    public ConsolaOperador(InventarioGeneral inventario) {
        this.inventario = inventario;
        this.ofertasRegistradas = new HashMap<>();
    }

    protected void mostrarMenuPrincipal() throws IOException {
        System.out.println("Bienvenido a la consola del Operador.");
        if (operador == null) {
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
            mostrarOpcionesDeOperador();
        }
    }

    private void iniciarSesion() throws IOException {
        String username = pedirCadenaAlUsuario("Ingrese su nombre de usuario:");
        String password = pedirCadenaAlUsuario("Ingrese su contraseña:");

        if (FileUtils.verifyUser(username, password) && "operador".equals(FileUtils.getRole(username))) {
            this.operador = new Operador(username, "Operador", username, password, "operador");
            System.out.println("Inicio de sesión exitoso.");
            mostrarOpcionesDeOperador();
        } else {
            System.out.println("Inicio de sesión fallido. Intente nuevamente.");
            mostrarMenuPrincipal();
        }
    }

    private void registrarNuevoUsuario() throws IOException {
        String username = pedirCadenaAlUsuario("Ingrese un nombre de usuario:");
        String password = pedirCadenaAlUsuario("Ingrese una contraseña:");

        FileUtils.registerUser(username, password, "operador");
        System.out.println("Usuario registrado exitosamente como operador. Por favor inicie sesión.");
        mostrarMenuPrincipal();
    }

    private void mostrarOpcionesDeOperador() {
        System.out.println("Opciones de Operador:");
        System.out.println("1. Registrar Oferta");
        System.out.println("2. Salir");
        int opcion = pedirEnteroAlUsuario("Seleccione una opción:");
        switch (opcion) {
            case 1:
                registrarOferta();
                break;
            case 2:
                System.out.println("Saliendo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida, intente de nuevo.");
                mostrarOpcionesDeOperador();
                break;
        }
    }

    private void registrarOferta() {
        String idPieza = pedirCadenaAlUsuario("Ingrese el ID de la pieza:");
        double montoOferta = pedirNumeroAlUsuario("Ingrese el monto de la oferta:");
        ofertasRegistradas.put(idPieza, montoOferta);
        System.out.println("Oferta registrada exitosamente para la pieza: " + idPieza);
    }
}