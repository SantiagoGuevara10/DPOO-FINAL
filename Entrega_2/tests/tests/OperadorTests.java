package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import galeria.usuarios.Operador;

class OperadorTests {
    private Operador operador;

    @BeforeEach
    void setUp() {
        operador = new Operador("op01", "Operador Uno", "operador1", "pass1", "Operador");
    }

    @Test
    void testAgregarOferta() {
        operador.getOfertasRegistradas().put("pieza01", 10000.0);
        assertEquals(10000.0, operador.getOfertasRegistradas().get("pieza01"), "Debería retornar el valor correcto de la oferta.");
    }

    @Test
    void testEliminarOferta() {
        operador.getOfertasRegistradas().put("pieza01", 10000.0);
        operador.getOfertasRegistradas().remove("pieza01");
        assertFalse(operador.getOfertasRegistradas().containsKey("pieza01"), "La oferta debería haber sido eliminada.");
    }

    @Test
    void testObtenerOfertas() {
        operador.getOfertasRegistradas().put("pieza01", 10000.0);
        operador.getOfertasRegistradas().put("pieza02", 20000.0);
        assertEquals(2, operador.getOfertasRegistradas().size(), "Debería haber dos ofertas registradas.");
    }

    @Test
    void testModificarOferta() {
        operador.getOfertasRegistradas().put("pieza01", 10000.0);
        operador.getOfertasRegistradas().put("pieza01", 15000.0);
        assertEquals(15000.0, operador.getOfertasRegistradas().get("pieza01"), "El valor de la oferta debería haber sido modificado.");
    }
}
