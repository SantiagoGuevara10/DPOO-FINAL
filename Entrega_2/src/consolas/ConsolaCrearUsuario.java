package consolas;

import java.util.Arrays;
import java.util.Collection;

public class ConsolaCrearUsuario extends ConsolaBasica {
	
	/**
     * Opciones que se mostrarán en el menú para crear una gasolinera
     */
    private final String[] opcionesCrearUsuario = new String[]{ "Nuevo administrador","Nuevo cajero","Nuevo operador", "Nuevo comprador o propietario", "salir"};
    

    /**
     * Muestra todas las opciones para configurar un nuevo usuario.
     * @return 
     * @return Retorna un nuevo usuario
     */
    public Object mostrarMenu( )
    {
        boolean regresar = false;
        Object usuario = null;

        while( !regresar )
        {
            int opcionSeleccionada = mostrarMenu( "Opciones para crear Usuario", opcionesCrearUsuario);
            if( opcionSeleccionada == 1 )
            {
            	crearAdmin();
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
                regresar = true;
            }
        }
        
        return usuario;
    }


	private void crearAdmin() {
		
		
	}
    
    
    
       
    
    



}