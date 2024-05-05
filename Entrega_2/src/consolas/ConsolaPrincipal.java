package consolas;

import galeria.inventarios.InventarioGeneral;

/**
 * En esta clase se encuentra el método main de la aplicación.
 *//**
 * En esta clase se encuentra el método main de la aplicación.
 */
public class ConsolaPrincipal extends ConsolaBasica{
	/**
     * Opciones que se mostrarán en el menú principal
     */
    private final String[] opcionesMenuPrincipal = new String[]{"Ingresar como Empleado","Ingresar como cliente","Cargar inventario","Cargar usuarios","Salir" };

    private InventarioGeneral inventario;
    
    /**
     * Muestra el menú principal de la aplicación y ejecuta la opción que seleccione el usuario.
     * 
     * El menú vuelve a mostrarse hasta que el usuario seleccione la opción para abandonar la aplicación.
     */
    private void mostrarMenuPrincipal( )
    {
        int opcionSeleccionada = mostrarMenu( "Menú principal", opcionesMenuPrincipal );
        if( opcionSeleccionada == 1 )
        {
            
        }
        else if( opcionSeleccionada == 2 )
        {
            ConsolaCrearGasolinera consolaCreacion = new ConsolaCrearGasolinera( );
            laGasolinera = consolaCreacion.mostrarOpciones( );
        }
        else if( opcionSeleccionada == 3 )
        {
            cargarGasolinera( );
        }
        else if( opcionSeleccionada == 4 )
        {
            guardarGasolinera( );
        }
        else if( opcionSeleccionada == 5 )
        {
            System.out.println( "Saliendo ..." );
            System.exit( 0 );
        }
        mostrarMenuPrincipal( );
    }
}
