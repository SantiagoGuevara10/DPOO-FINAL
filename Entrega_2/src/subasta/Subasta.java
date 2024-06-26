package subasta;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.pieza.Pieza;
import galeria.usuarios.CompradorPropietario;
import galeria.usuarios.Administrador;

public class Subasta {
	private Map<String, Pieza> piezasDisponibles;
	private Map<String, List<Oferta>> ofertas;
	private List<CompradorPropietario> compradores;
	
	public Subasta(List<Pieza> piezas) {
		this.piezasDisponibles = new HashMap<String, Pieza>();
		this.ofertas = new HashMap<String, List<Oferta>>();
		for (int i = 0; i < piezas.size(); i++) {
			Pieza pieza = piezas.get(i);
			String id = pieza.getIdPieza();
			this.piezasDisponibles.put(id, pieza);
			this.ofertas.put(id, new LinkedList<Oferta>());
		}
	}
	
	public void crearOferta(CompradorPropietario comprador, String piezaId, int dinero) {
		Pieza pieza = this.piezasDisponibles.get(piezaId);
		Oferta oferta = new Oferta(comprador, pieza, dinero);
		this.ofertas.get(piezaId).add(oferta);
	}
	
	public Oferta getMaximaOferta(String piezaId) {
		List<Oferta> ofertas = this.getOfertas().get(piezaId);
		if (ofertas.size() <= 0) {
			return null;
		}
		Oferta maxima = ofertas.get(0);
		for (int i = 0; i < ofertas.size(); i++) {
			Oferta oferta = ofertas.get(i);
			if (oferta.getDinero() > maxima.getDinero()) {
				maxima = oferta;
			}
		}
		return maxima;
	}
	public Map<String, Pieza> getPiezasDisponibles() {
		return piezasDisponibles;
	}

	public void setPiezasDisponibles(Map<String, Pieza> piezasDisponibles) {
		this.piezasDisponibles = piezasDisponibles;
	}

	public Map<String, List<Oferta>> getOfertas() {
		return ofertas;
	}

	public void setOfertas(Map<String, List<Oferta>> ofertas) {
		this.ofertas = ofertas;
	}

	public List<CompradorPropietario> getCompradores() {
		return compradores;
	}

	public void setCompradores(List<CompradorPropietario> compradores) {
		this.compradores = compradores;
	}
}