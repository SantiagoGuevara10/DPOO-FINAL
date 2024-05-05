package subasta;

import java.util.List;

import galeria.pieza.Pieza;
import galeria.usuarios.CompradorPropietario;

public class Oferta {
	private int dinero;
	private CompradorPropietario comprador;
	private Pieza pieza;
	
	public Oferta(CompradorPropietario comprador, Pieza pieza, int dinero) {
		this.comprador = comprador;
		this.pieza = pieza;
		this.dinero = dinero;
	}
	
	public int getDinero() {
		return dinero;
	}
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}
	public CompradorPropietario getComprador() {
		return comprador;
	}
	public void setComprador(CompradorPropietario comprador) {
		this.comprador = comprador;
	}
	public Pieza getPieza() {
		return pieza;
	}
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
}