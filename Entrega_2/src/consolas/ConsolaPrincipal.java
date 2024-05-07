package consolas;

import galeria.inventarios.InventarioGeneral;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import galeria.usuarios.FileUtils;

public class ConsolaPrincipal extends ConsolaBasica {
    private InventarioGeneral inventario;

    public ConsolaPrincipal() {
        this.inventario = new InventarioGeneral(); 
    }

    private void mostrarMenuPrincipal() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Menú Principal:");
            System.out.println("1. Ingresar como Empleado");
            System.out.println("2. Ingresar como CompradorPropietario");
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

        if (FileUtils.verifyUser(username, password) && tipoUsuario.equals(FileUtils.getRole(username))) {
            System.out.println("Autenticación exitosa. Bienvenido " + tipoUsuario + ".");
            lanzarConsolaUsuario(tipoUsuario);  
        } else {
            System.out.println("Credenciales incorrectas o rol incorrecto.");
            mostrarMenuPrincipal();
        }
    }

   
    private void lanzarConsolaUsuario(String tipoUsuario) throws IOException {
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
                ConsolaCompradorPropietario consolaComprador = new ConsolaCompradorPropietario(inventario);
                consolaComprador.mostrarMenuPrincipal();
                break;
            default:
                System.out.println("Tipo de usuario no reconocido, regresando al menú principal.");
                mostrarMenuPrincipal();
                break;
        }
    }

    private void registrarNuevoUsuario(BufferedReader reader) throws IOException {
        System.out.print("Ingrese un nombre de usuario: ");
        String username = reader.readLine();
        System.out.print("Ingrese una contraseña: ");
        String password = reader.readLine();
        System.out.print("Ingrese el tipo de usuario (Administrador, Operador, Cajero, CompradorPropietario): ");
        String tipoUsuario = reader.readLine();

        FileUtils.registerUser(username, password, tipoUsuario);
        System.out.println("Usuario registrado exitosamente. Por favor inicie sesión.");
        lanzarConsolaUsuario(tipoUsuario);
    }

    public static void main(String[] args) throws IOException {
        new ConsolaPrincipal().mostrarMenuPrincipal();
    }
}
