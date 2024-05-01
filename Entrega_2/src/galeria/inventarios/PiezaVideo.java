package galeria.inventarios;

import java.util.Date;
import java.util.List;
import java.util.Map;
import galeria.pieza.Pieza;
import galeria.usuarios.Propietario;

public class PiezaVideo extends Pieza {
    private String calidad;
    private int duracion;

    public PiezaVideo(String idPieza, String titulo, int anioCreacion, String lugarCreacion,
                      String estadoPieza, boolean estaExhibida, boolean disponibleVenta,
                      List<String> autores, double valorFijo, int valorMinimo, int valorInicial,
                      Date fechaDeIngreso, boolean esVigente, String descripcion, Propietario propietario,
                      String calidad, int duracion) {
        super(idPieza, titulo, anioCreacion, lugarCreacion, estadoPieza, estaExhibida, disponibleVenta,
              autores, valorFijo, valorMinimo, valorInicial, fechaDeIngreso, esVigente, descripcion, propietario);
        this.calidad = calidad;
        this.duracion = duracion;
    }
    
	
	public String getCalidad() {
		return calidad;
	}
	
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion( int duracion) {
	    this.duracion = duracion;
	}

}