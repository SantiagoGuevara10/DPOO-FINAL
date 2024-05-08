package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import galeria.usuarios.Administrador;
import galeria.usuarios.CompradorPropietario;
import java.util.ArrayList;

public class VerificacionCompradorTest {
    private Administrador admin;
    private CompradorPropietario comprador;

    @BeforeEach
    public void setup() {
        admin = new Administrador("admin02", "Admin User", "admin", "adminpass", "Administrador");
        comprador = new CompradorPropietario("cp02", "Comprador Dos", "comprador2", "pass2", "contacto2", 5000, false, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void testVerificarComprador() {
        admin.verificarUsuario(comprador);
        assertTrue(comprador.isEstaVerificado());
    }
}
