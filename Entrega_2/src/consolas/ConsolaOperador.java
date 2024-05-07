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

    protected void mostrarMenuPrincipal() throws IOException  {
        System.out.println("Bienvenido a la consola de operador, ");
        mostrarOpcionesDeOperador();
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