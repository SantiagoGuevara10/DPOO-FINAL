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
    private final String[] opcionesMenuPrincipal = new String[]{"Ingresar como Empleado","Ingresar como cliente","Crear Usuario","Salir" };

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
            
        }
        else if( opcionSeleccionada == 3 )
        {
            
        }
        else if( opcionSeleccionada == 4 )
        {
           
        }
        else if( opcionSeleccionada == 5 )
        {
            
        }
        else if( opcionSeleccionada == 6 )
        {
            System.out.println( "Saliendo ..." );
            System.exit( 0 );
        }
        mostrarMenuPrincipal( );
    }
    
    public static void main( String[] args )
    {
        ConsolaPrincipal c = new ConsolaPrincipal( );
        c.mostrarMenuPrincipal( );
    }
}
