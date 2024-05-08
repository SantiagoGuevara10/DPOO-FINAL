package consolas;

import galeria.inventarios.InventarioGeneral;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import galeria.usuarios.Empleado;
import galeria.usuarios.CompradorPropietario;
import galeria.usuarios.FileUtils;
import galeria.usuarios.UsuariosRegistrados;

public class ConsolaPrincipal extends ConsolaBasica {
    private InventarioGeneral inventario;
    private UsuariosRegistrados usuariosDelPrograma;
    private File archivo = new File( "./datos/" + "Usuarios" );
	private File archivo2 = new File( "./datos/" + "Inventario" );

    public ConsolaPrincipal() throws NumberFormatException, FileNotFoundException, IOException, ParseException {
    	

        this.inventario = InventarioGeneral.cargarEstado(archivo2);
        this.usuariosDelPrograma= UsuariosRegistrados.cargarEstado(archivo);
    }

    private void mostrarMenuPrincipal() throws IOException {
    	
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Menú Principal:");
            System.out.println("1. Ingresar como Empleado");
            System.out.println("2. Ingresar como Comprador o Propietario");
            System.out.println("3. Crear Usuario");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcionSeleccionada = 0;
            try {
                opcionSeleccionada = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue; 
            }

            switch (opcionSeleccionada) {
                case 1:
                    seleccionarTipoEmpleado(reader);
                    break;
                case 2:
                    ConsolaCompradorPropietario cocaje = new ConsolaCompradorPropietario(inventario, usuariosDelPrograma, archivo);
                    cocaje.autenticarUsuario("CompradorPropietario", reader);
                    cocaje.mostrarOpcionesCompradorPropietario();
                    autenticarUsuario("CompradorPropietario", reader);
                    break;
                case 3:
                    registrarNuevoUsuario(reader);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    return; 
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        }
    }

    private void seleccionarTipoEmpleado(BufferedReader reader) throws IOException {
        System.out.println("Seleccione el tipo de empleado:");
        System.out.println("1. Administrador");
        System.out.println("2. Operador");
        System.out.println("3. Cajero");
        System.out.print("Seleccione una opción: ");
        int tipo = Integer.parseInt(reader.readLine());

        switch (tipo) {
            case 1:
                autenticarUsuario("Administrador", reader);
                break;
            case 2:
                autenticarUsuario("Operador", reader);
                break;
            case 3:
                autenticarUsuario("Cajero", reader);
                break;
            default:
                System.out.println("Opción no válida.");
                seleccionarTipoEmpleado(reader); 
                break;
        }
    }

    private void autenticarUsuario(String tipoUsuario, BufferedReader reader) throws IOException {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = reader.readLine();
        System.out.print("Ingrese su contraseña: ");
        String password = reader.readLine();

        boolean autenticado = false;

        
        for (Empleado empleado : usuariosDelPrograma.getUsuariosEnPrograma()) {
            if (empleado.getUsername().equals(username) && empleado.getPasswordHash().equals(password) && empleado.getRole().equals(tipoUsuario)) {
                autenticado = true;
                break;
            }
        }

        
        if (!autenticado && tipoUsuario.equals("CompradorPropietario")) {
            for (CompradorPropietario comprador : usuariosDelPrograma.getCompradoresEnPrograma()) {
                if (comprador.getUsername().equals(username) && comprador.getPasswordHash().equals(password)) {
                    autenticado = true;
                    break;
                }
            }
        }

        if (autenticado) {
            System.out.println("Autenticación exitosa. Bienvenido " + tipoUsuario + ".");
            lanzarConsolaUsuario(tipoUsuario);  
        } else {
            System.out.println("Credenciales incorrectas o rol incorrecto.");
        }
    }

   
    private void lanzarConsolaUsuario(String tipoUsuario) throws IOException {
    	File archivo = new File( "./datos/" + "Usuarios" );
        switch (tipoUsuario) {
        
            case "Administrador":
                ConsolaAdministrador consolaAdministrador = new ConsolaAdministrador(inventario);
                consolaAdministrador.mostrarMenuPrincipal();
                break;
            case "Operador":
                ConsolaOperador consolaOperador = new ConsolaOperador(inventario);
                consolaOperador.mostrarMenuPrincipal();
                break;
            case "Cajero":
                ConsolaCajero consolaCajero = new ConsolaCajero(inventario);
                consolaCajero.mostrarMenuPrincipal();
                break;
            case "CompradorPropietario":
                ConsolaCompradorPropietario consolaComprador = new ConsolaCompradorPropietario(inventario, usuariosDelPrograma, archivo);
                consolaComprador.mostrarMenuPrincipal();
                break;
            default:
                System.out.println("Tipo de usuario no reconocido, regresando al menú principal.");
                mostrarMenuPrincipal();
                break;
        }
    }

    private void registrarNuevoUsuario(BufferedReader reader) throws IOException {
    	String[] opciones = new String[]{ "Administrador", "Operador", "Cajero", "Comprador o Propietario" };
        

        int opcionSeleccionada = mostrarMenu( "Ingrese el tipo de usuario", opciones );
        String tipoUsuario = null;
		if( opcionSeleccionada == 1 )
        {
			
			
			File archivo = new File( "./datos/" + "Usuarios" );
            tipoUsuario = "Administrador";
            ConsolaAdministrador cadmi = new ConsolaAdministrador(inventario);
            cadmi.crearUsuario(usuariosDelPrograma);
            usuariosDelPrograma.guardarUsuarios(archivo);
            
        }
        else if( opcionSeleccionada == 2 )
        {
        	File archivo = new File( "./datos/" + "Usuarios" );
        	tipoUsuario = "Operador";
            ConsolaOperador coper = new ConsolaOperador(inventario);
            coper.crearUsuario(usuariosDelPrograma);
            usuariosDelPrograma.guardarUsuarios(archivo);
            

        }
        else if( opcionSeleccionada == 3 )
        {
        	File archivo = new File( "./datos/" + "Usuarios" );
            tipoUsuario = "Cajero";
            ConsolaCajero cocaje = new ConsolaCajero(inventario);
            cocaje.crearUsuario(usuariosDelPrograma);
            usuariosDelPrograma.guardarUsuarios(archivo);
        }
        else if( opcionSeleccionada == 4 )
        {
        	File archivo = new File( "./datos/" + "Usuarios" );
            tipoUsuario = "CompradorPropietario";
<<<<<<< HEAD
            ConsolaCompradorPropietario cocaje = new ConsolaCompradorPropietario(inventario, usuariosDelPrograma, archivo);
            cocaje.crearUsuario(usuariosDelPrograma);
=======
            ConsolaCompradorPropietario cocaje = new ConsolaCompradorPropietario(inventario);
            cocaje.crearUsuario(usuariosDelPrograma);
            usuariosDelPrograma.guardarUsuarios(archivo);
>>>>>>> bc3ed1fbd53a4ee9da5fdb2c1f57d419e0e623de
            cocaje.mostrarOpcionesCompradorPropietario();
            

        }
        
        

        

        

     }
    public static void main(String[] args) throws IOException, Exception, ParseException {
        new ConsolaPrincipal().mostrarMenuPrincipal();
    }
}
