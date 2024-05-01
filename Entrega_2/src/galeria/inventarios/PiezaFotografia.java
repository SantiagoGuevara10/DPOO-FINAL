package galeria.inventarios;

import java.util.Date;
import java.util.List;
import java.util.Map;
import galeria.pieza.Pieza;
import galeria.usuarios.Propietario;

public class PiezaFotografia extends Pieza {
    private boolean esDigital;

    public PiezaFotografia(String idPieza, String titulo, int anioCreacion, String lugarCreacion,
                           String estadoPieza, boolean estaExhibida, boolean disponibleVenta,
                           List<String> autores, double valorFijo, int valorMinimo, int valorInicial,
                           Date fechaDeIngreso, boolean esVigente, String descripcion, Propietario propietario,
                           boolean esDigital) {
        super(idPieza, titulo, anioCreacion, lugarCreacion, estadoPieza, estaExhibida, disponibleVenta,
              autores, valorFijo, valorMinimo, valorInicial, fechaDeIngreso, esVigente, descripcion, propietario);
        this.esDigital = esDigital;
    }
	
	public boolean getEsDigital() {
		return esDigital;
	}
	
	public void setEsDigital(boolean esDigital) {
		this.esDigital = esDigital;
	}
	
	
	
}
