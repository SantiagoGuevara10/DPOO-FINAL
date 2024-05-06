package galeria.usuarios;
 
import galeria.usuarios.Empleado;
import galeria.usuarios.FileUtils;
import galeria.usuarios.CompradorPropietario;
 
 
public class ManejoSesion {
    private static Empleado empleadoActual;
    private static CompradorPropietario compradorPropietarioActual;
 
    public static void loginEmpleado(String username, String password) {
        UsuariosRegistrados usuariosRegistrados = new UsuariosRegistrados(null, null);
        if (FileUtils.verifyUser(username, password)) {
            for (Empleado empleado : usuariosRegistrados.getUsuariosEnPrograma()) {
                if (empleado.getUsername().equals(username)) {
                    empleadoActual = empleado;
                    
                    return;
                }
            }
           
        } else {
            
        }
    }
 
    public static void loginCompradorPropietario(String username, String password) {
        UsuariosRegistrados usuariosRegistrados = new UsuariosRegistrados(null, null);
        if (FileUtils.verifyUser(username, password)) {
            for (CompradorPropietario comprador : usuariosRegistrados.getCompradoresEnPrograma()) {
                if (comprador.getUsername().equals(username)) {
                    compradorPropietarioActual = comprador;
                  
                    return;
                }
            }
            
        } else {
           
        }
    }
 
    public static Empleado getEmpleadoActual() {
        return empleadoActual;
    }
 
    public static CompradorPropietario getCompradorPropietarioActual() {
        return compradorPropietarioActual;
    }
 
    public static void logout() {
        empleadoActual = null;
        compradorPropietarioActual = null;
    }
}
