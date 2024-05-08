package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import galeria.pieza.Pieza;
import galeria.usuarios.CompradorPropietario;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

class CompradorPropietarioTests {
    private CompradorPropietario comprador;
    private Pieza pieza1;
    private Pieza pieza2;
    private Pieza piezaFavorita;

    @BeforeEach
    void setUp() {
        List<String> autores = Arrays.asList("Autor A");
        pieza1 = new Pieza("pieza01", "Pieza Uno", 2020, "Lugar A", "vendida", false, true, autores, 1000.0, 500, 1000, null, true, "Descripción 1");
        pieza2 = new Pieza("pieza02", "Pieza Dos", 2018, "Lugar B", "vendida", false, true, autores, 2000.0, 1000, 2000, null, true, "Descripción 2");
        piezaFavorita = new Pieza("pieza03", "Pieza Favorita", 2019, "Lugar C", "vendida", false, true, autores, 1500.0, 750, 1500, null, true, "Descripción 3");

        comprador = new CompradorPropietario("cp01", "Comprador Uno", "comprador1", "pass1", "correo@comprador.com", 5000.0, true, new LinkedList<>(), new LinkedList<>());
    }

    @Test
    void testAgregarPieza() {
        comprador.agregarPieza(pieza1);
        comprador.agregarPieza(pieza2);

        List<Pieza> piezas = comprador.getPiezas();
        assertEquals(2, piezas.size());
        assertTrue(piezas.contains(pieza1));
        assertTrue(piezas.contains(pieza2));
    }

    @Test
    void testAgregarPiezaFavorita() {
        comprador.setPiezaFav(piezaFavorita);

        List<Pieza> piezasFavoritas = comprador.getPiezasFvoritas();
        assertEquals(1, piezasFavoritas.size());
        assertTrue(piezasFavoritas.contains(piezaFavorita));
    }

    @Test
    void testModificarInformacionContacto() {
        String nuevaInformacionContacto = "nuevo@correo.com";
        comprador.setInformacionContacto(nuevaInformacionContacto);

        assertEquals(nuevaInformacionContacto, comprador.getInformacionContacto());
    }

    @Test
    void testModificarDinero() {
        double nuevoMonto = 3000.0;
        comprador.setDinero(nuevoMonto);

        assertEquals(nuevoMonto, comprador.getDinero());
    }
}