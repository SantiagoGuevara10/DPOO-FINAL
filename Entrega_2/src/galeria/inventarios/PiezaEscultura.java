package galeria.inventarios;

import java.util.Date;
import java.util.List;
import java.util.Map;
import galeria.pieza.Pieza;
import galeria.usuarios.Propietario;

public class PiezaEscultura extends Pieza {
    private float peso;
    private boolean usaElectricidad;

    public PiezaEscultura(String idPieza, String titulo, int anioCreacion, String lugarCreacion,
                          String estadoPieza, boolean estaExhibida, boolean disponibleVenta,
                          List<String> autores, double valorFijo, int valorMinimo, int valorInicial,
                          Date fechaDeIngreso, boolean esVigente, String descripcion, Propietario propietario,
                          float peso, boolean usaElectricidad) {
        super(idPieza, titulo, anioCreacion, lugarCreacion, estadoPieza, estaExhibida, disponibleVenta,
              autores, valorFijo, valorMinimo, valorInicial, fechaDeIngreso, esVigente, descripcion, propietario);
        this.peso = peso;
        this.usaElectricidad = usaElectricidad;
    }
	
	public float getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	
	public boolean getUsaElectricidad() {
		return usaElectricidad;
	}
	
	public void setUsaElectricidad(boolean usaElectricidad) {
		this.usaElectricidad = usaElectricidad;
	}
}