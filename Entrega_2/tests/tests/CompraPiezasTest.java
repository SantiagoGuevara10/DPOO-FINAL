package tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import galeria.usuarios.CompradorPropietario;
import galeria.inventarios.*;
import galeria.pieza.*;
import java.util.ArrayList;

public class CompraPiezasTest {
    private InventarioGeneral inventario;
    private CompradorPropietario comprador;

    @BeforeEach
    public void setup() {
        inventario = new InventarioGeneral();
        comprador = new CompradorPropietario("cp01", "Comprador Uno", "comprador1", "pass1", "contacto1", 10000, false, new ArrayList<>(), new ArrayList<>());
        Pieza pieza = new Pieza("p002", "El Grito", 1893, "Oslo", "exhibida", true, true, null, 7000.0, 6500, 6800, new java.util.Date(), true, "Un ícono del expresionismo.");
        inventario.addInventarioExhibido("p002", pieza);
    }

    @Test
    public void testCompraPiezaDisponible() {
        comprador.agregarPieza(inventario.getPiezaInventarioExhibido("p002"));
        assertEquals(3000, comprador.getDinero());
        assertFalse(inventario.getPiezasDisponibles().containsKey("p002"));
    }
    @Test
    public void testHistorialPiezaDisponible() {
    	int tamanoInicial = inventario.getPiezaInventarioExhibido("p002").getHistorialPropietarios().size();
    	comprador.agregarPieza(inventario.getPiezaInventarioExhibido("p002"));
    	assertFalse(inventario.getPiezaInventarioExhibido("p002").getHistorialPropietarios().size() >= tamanoInicial);
    }
}
