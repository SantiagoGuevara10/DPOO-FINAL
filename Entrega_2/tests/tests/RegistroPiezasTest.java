package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import galeria.usuarios.Administrador;
import galeria.inventarios.InventarioGeneral;
import galeria.pieza.Pieza;

public class RegistroPiezasTest {
    private InventarioGeneral inventario;
    private Administrador admin;

    @BeforeEach
    public void setup() {
        inventario = new InventarioGeneral();
        admin = new Administrador("admin01", "Admin User", "admin", "adminpass", "Administrador");
    }

    @Test
    public void testAgregarPieza() {
        Pieza nuevaPieza = new Pieza("p001", "La Libertad guiando al pueblo", 1830, "París", "bodega", false, true, null, 5000.0, 4500, 4800, new java.util.Date(), true, "Una pintura icónica de la Revolución Francesa.");
        admin.agregarPieza(nuevaPieza, inventario);
        assertTrue(inventario.getPiezasDisponibles().containsKey("p001"));
        assertEquals("La Libertad guiando al pueblo", inventario.getPiezaInventarioBodega("p001").getTitulo());
    }
}
