package subasta;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import galeria.pieza.Pieza;
import galeria.usuarios.Comprador;
import galeria.usuarios.Administrador;

public class Subasta {
	private Map<String, Pieza> piezasDisponibles;
	private Map<String, List<Oferta>> ofertas;
	private List<Comprador> compradores;
	
	public Subasta(List<Pieza> piezas, List<Comprador> compradores) {
		this.piezasDisponibles = new HashMap<String, Pieza>();
		this.ofertas = new HashMap<String, List<Oferta>>();
		this.compradores = compradores;
		for (int i = 0; i < piezas.size(); i++) {
			Pieza pieza = piezas.get(i);
			String id = pieza.getIdPieza();
			this.piezasDisponibles.put(id, pieza);
			this.ofertas.put(id, new LinkedList<Oferta>());
		}
	}
	
	public void crearOferta(Comprador comprador, String piezaId, int dinero) {
		Pieza pieza = this.piezasDisponibles.get(piezaId);
		Oferta oferta = new Oferta(comprador, pieza, dinero);
		this.ofertas.get(piezaId).add(oferta);
	}
	
	public void mandarOferta(String piezaId) {
		Oferta maxima = this.getMaximaOferta(piezaId);
		if (maxima != null) {
			Administrador.agregarOferta(maxima);
		} else {
			System.out.println("No existen ofertas.");
		}
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

	public List<Comprador> getCompradores() {
		return compradores;
	}

	public void setCompradores(List<Comprador> compradores) {
		this.compradores = compradores;
	}
}