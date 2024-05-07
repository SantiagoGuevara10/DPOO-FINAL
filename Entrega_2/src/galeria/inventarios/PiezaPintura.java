package galeria.inventarios;

import java.util.Date;
import java.util.List;
import galeria.pieza.Pieza;
import galeria.usuarios.CompradorPropietario;

public class PiezaPintura extends Pieza {
    private int peso;
    private String tecnica;

    public PiezaPintura(String idPieza, String titulo, int anioCreacion, String lugarCreacion,
                        String estadoPieza, boolean estaExhibida, boolean disponibleVenta,
                        List<String> autores, double valorFijo, int valorMinimo, int valorInicial,
                        Date fechaDeIngreso, boolean esVigente, String descripcion, CompradorPropietario propietario,
                        int peso, String tecnica) {
        super(idPieza, titulo, anioCreacion, lugarCreacion, estadoPieza, estaExhibida, disponibleVenta,
              autores, valorFijo, valorMinimo, valorInicial, fechaDeIngreso, esVigente, descripcion);
        this.peso = peso;
        this.tecnica = tecnica;
    }
	public int getPeso() {
		return peso;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public String getTecnica() {
		return tecnica;
	}
	
	public void setTecnica (String tecnica) {
		this.tecnica = tecnica;
	}
	
	

}