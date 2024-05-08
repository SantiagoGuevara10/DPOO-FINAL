package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import galeria.usuarios.Administrador;
import galeria.usuarios.CompradorPropietario;
import galeria.pieza.Pieza;
import galeria.inventarios.InventarioGeneral;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

class AdministradorTests {
    private Administrador admin;
    private CompradorPropietario comprador;
    private InventarioGeneral inventario;
    private Pieza piezaBodega;
    private Pieza piezaExhibida;

    @BeforeEach
    void setUp() {
        admin = new Administrador("admin01", "Administrador", "admin1", "adminpass", "Administrador");

        List<String> autores = Arrays.asList("Autor A");
        piezaBodega = new Pieza("pieza01", "Pieza en Bodega", 2020, "Lugar A", "bodega", false, true, autores, 1000.0, 500, 1000, null, true, "Descripción Bodega");
        piezaExhibida = new Pieza("pieza02", "Pieza Exhibida", 2018, "Lugar B", "exhibida", false, true, autores, 2000.0, 1000, 2000, null, true, "Descripción Exhibida");

        comprador = new CompradorPropietario("cp01", "Comprador Uno", "comprador1", "pass1", "correo@comprador.com", 5000.0, true, new LinkedList<>(), new LinkedList<>());

        inventario = new InventarioGeneral();
    }

    @Test
    void testAgregarPieza() {
        admin.agregarPieza(piezaBodega, inventario);
        admin.agregarPieza(piezaExhibida, inventario);

        assertTrue(inventario.getInventarioBodega().containsKey(piezaBodega.getIdPieza()));
        assertTrue(inventario.getInventarioExhibido().containsKey(piezaExhibida.getIdPieza()));
    }

    @Test
    void testDevolverPieza() {
        inventario.addInventarioBodega(piezaBodega.getIdPieza(), piezaBodega);
        inventario.addInventarioExhibido(piezaExhibida.getIdPieza(), piezaExhibida);

        admin.devolverPieza(piezaBodega, inventario, comprador);
        admin.devolverPieza(piezaExhibida, inventario, comprador);

        assertFalse(inventario.getInventarioBodega().containsKey(piezaBodega.getIdPieza()));
        assertFalse(inventario.getInventarioExhibido().containsKey(piezaExhibida.getIdPieza()));
        assertTrue(comprador.getPiezas().contains(piezaBodega));
        assertTrue(comprador.getPiezasFvoritas().contains(piezaExhibida));
    }

    @Test
    void testVerificarUsuario() {
        comprador.setEstaVerificado(false);
        admin.verificarUsuario(comprador);

        assertTrue(comprador.isEstaVerificado());
    }

    @Test
    void testVerHistoriaCompras() {
        comprador.agregarPieza(piezaBodega);
        comprador.agregarPieza(piezaExhibida);

        String historial = admin.verHistoriaCompras(comprador);
        assertTrue(historial.contains("Pieza en Bodega"));
        assertTrue(historial.contains("Pieza Exhibida"));
    }

    @Test
    void testCalcularValorColeccion() {
        comprador.agregarPieza(piezaBodega);
        comprador.agregarPieza(piezaExhibida);

        double valor = admin.calcularValorColeccion(comprador);
        assertEquals(3000.0, valor);
    }
}
