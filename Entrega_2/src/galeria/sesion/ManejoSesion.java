package galeria.sesion;

import galeria.usuarios.Empleado;
import galeria.usuarios.FileUtils;
import galeria.usuarios.CompradorPropietario;
import galeria.usuarios.UserManager;

public class ManejoSesion {
    private static Empleado currentEmployee;
    private static CompradorPropietario currentCompradorPropietario;

    public static void loginEmpleado(String username, String password) {
        if (FileUtils.verifyUser(username, password)) {
            currentEmployee = UserManager.getEmpleado(username);
            if (currentEmployee != null) {
               
            } else {
                
            }
        } else {
            
        }
    }

    public static void loginCompradorPropietario(String username, String password) {
        if (FileUtils.verifyUser(username, password)) {
            currentCompradorPropietario = UserManager.getCompradorPropietario(username);
            if (currentCompradorPropietario != null) {
                
            } else {
                
            }
        } else {
         
        }
    }

    public static Empleado getCurrentEmployee() {
        return currentEmployee;
    }

    public static CompradorPropietario getCurrentCompradorPropietario() {
        return currentCompradorPropietario;
    }

    public static void logout() {
        if (currentEmployee != null) {
            
            currentEmployee = null;
        } else if (currentCompradorPropietario != null) {
            
            currentCompradorPropietario = null;
        } else {
            
        }
    }
}
