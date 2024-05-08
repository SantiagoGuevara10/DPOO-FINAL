package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import galeria.pieza.Pieza;
import galeria.usuarios.Cajero;
import galeria.usuarios.CompradorPropietario;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

class CajeroTests {
    private Cajero cajero;
    private CompradorPropietario comprador;
    private CompradorPropietario vendedor;
    private Pieza pieza;

    @BeforeEach
    void setUp() {
        cajero = new Cajero("cj01", "Cajero Uno", "cajero1", "pass1", "Cajero");

        
        comprador = new CompradorPropietario("cp01", "Comprador Uno", "comprador1", "pass1", "correo@comprador.com", 10000.0, true, new LinkedList<>(), new LinkedList<>());
        vendedor = new CompradorPropietario("vp01", "Vendedor Uno", "vendedor1", "pass1", "correo@vendedor.com", 5000.0, true, new LinkedList<>(), new LinkedList<>());
        List<String> autores = Arrays.asList("Autor A");
        pieza = new Pieza("pieza01", "Pieza de Arte", 2024, "Lugar de Creación", "vendida", false, true, autores, 1000.0, 500, 1000, null, true, "Descripción de la pieza");
    }

    @Test
    void testAgregarTransaccion() {
        String transaccion = "Compra de Pieza por Comprador";
        cajero.setTransacciones(transaccion);

        List<String> transacciones = cajero.getTransacciones();
        assertTrue(transacciones.contains(transaccion));
    }

    @Test
    void testProcesarPagoExitoso() {
        double monto = 2000.0;
        cajero.procesarPago(comprador, vendedor, monto, pieza);

        assertEquals(8000.0, comprador.getDinero());
        assertEquals(7000.0, vendedor.getDinero());
        assertTrue(cajero.getTransacciones().size() > 0);
    }

    @Test
    void testProcesarPagoInsuficiente() {
        double monto = 12000.0; 
        cajero.procesarPago(comprador, vendedor, monto, pieza);

        assertEquals(10000.0, comprador.getDinero());
        assertEquals(5000.0, vendedor.getDinero());
        assertTrue(cajero.getTransacciones().isEmpty());
    }
}