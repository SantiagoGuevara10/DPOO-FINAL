package consolas;

import galeria.usuarios.*;
import galeria.pieza.Pieza;
import galeria.sesion.ManejoSesion;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class ConsolaUsuarios {

    public static void main(String[] args) {
        try {
          
            registerNewUsers();
          
            FileUtils.loadUserCredentials(); 

           
            ManejoSesion.loginEmpleado("adminUsername", "adminPassword");
            Empleado empleadoActual = ManejoSesion.getCurrentEmployee();
            if(empleadoActual instanceof Administrador) {
                Administrador admin = (Administrador) empleadoActual;
                admin.realizarAccionesEspecificas();
                System.out.println("Usuario logueado: " + empleadoActual);
            }

    
            ManejoSesion.loginEmpleado("cashierUsername", "cashierPassword");
            empleadoActual = ManejoSesion.getCurrentEmployee();
            if(empleadoActual instanceof Cajero) {
                Cajero cajero = (Cajero) empleadoActual;
                cajero.realizarAccionesEspecificas();
                System.out.println("Usuario logueado: " + empleadoActual);
            }

          
            ManejoSesion.loginCompradorPropietario("buyerOwnerUsername", "buyerOwnerPassword");
            CompradorPropietario compradorPropietarioActual = ManejoSesion.getCurrentCompradorPropietario();
            if(compradorPropietarioActual != null) {
                compradorPropietarioActual.mostrarPiezas();
                Pieza pieza = crearPiezaDemo();
                compradorPropietarioActual.addPieza(pieza);
                compradorPropietarioActual.removePieza(pieza);
                System.out.println("Usuario logueado: " + compradorPropietarioActual);
            }

          
            empleadoActual = ManejoSesion.getCurrentEmployee();
            if(empleadoActual instanceof Operador) {
                Operador operador = (Operador) empleadoActual;
                operador.realizarAccionesEspecificas();
            }

        
            ManejoSesion.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registerNewUsers() throws IOException {
    
        FileUtils.registerUser("adminUsername", "adminPassword", "administrador");
        Empleado admin = new Administrador("1", "Admin User", "adminUsername", "adminPassword", "administrador");
        UserManager.registerUser("adminUsername", admin);
        System.out.println("Usuario registrado: " + admin);

       
        FileUtils.registerUser("CajeroUsername", "CajeroPassword", "cajero");
        Empleado cajero = new Cajero("2", "Cajero User", "CajeroUsername", "CajeroPassword", "cajero");
        UserManager.registerUser("cashierUsername", cajero);
        System.out.println("Usuario registrado: " + cajero);


        FileUtils.registerUser("CompradorPropietarioUsername", "CompradorPropietarioPassword", "comprador");
        CompradorPropietario CompradorPropietario = new CompradorPropietario("3", "CompradorPropietario", "CompradorPropietarioUsername", "buyerOwnerPassword", 50000000, true);
        UserManager.registerCompradorPropietario("CompradorPropietarioUsername", CompradorPropietario);
        System.out.println("Usuario registrado: " + CompradorPropietario);
    }

    private static Pieza crearPiezaDemo() {
        return new Pieza("P001", "La Gioconda", 1503, "Italia", "Bueno", true, true, Arrays.asList("Leonardo da Vinci"),
                         8000000.0, 7500000, 7000000, new Date(), true, "Una de las pinturas más reconocidas del mundo.",
                         new Propietario("Owner001", new ArrayList<>()));
    }
}