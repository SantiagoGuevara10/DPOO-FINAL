package galeria.usuarios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import subasta.Oferta;

public class Comprador extends CompradorPropietario {
    private static List<Comprador> compradores = new ArrayList<>();
    private List<Oferta> ofertasPendientes;
    private List<Oferta> ofertasAceptadas;
    private List<Oferta> ofertasRechazadas;
    
    public Comprador(String idUsuario, String nombre, String username, String informacionContacto, double limiteCompra, boolean estaVerificado) {
        super(idUsuario, nombre, username, informacionContacto, limiteCompra, estaVerificado);
        this.ofertasRechazadas = new LinkedList<>();
        this.ofertasAceptadas = new LinkedList<>();
        this.ofertasPendientes = new LinkedList<>();
        Comprador.compradores.add(this);
    }
	
	public static List<Comprador> getCompradores() {
		return compradores;
	}
	public List<Oferta> getOfertasPendientes() {
		return ofertasPendientes;
	}
	public void setOfertasPendientes(List<Oferta> ofertasPendientes) {
		this.ofertasPendientes = ofertasPendientes;
	}
	public List<Oferta> getOfertasAceptadas() {
		return ofertasAceptadas;
	}
	public void setOfertasAceptadas(List<Oferta> ofertasAceptadas) {
		this.ofertasAceptadas = ofertasAceptadas;
	}
	public List<Oferta> getOfertasRechazadas() {
		return ofertasRechazadas;
	}
	public void setOfertasRechazadas(List<Oferta> ofertasRechazadas) {
		this.ofertasRechazadas = ofertasRechazadas;
	}
	public static boolean isComprador(Comprador comprador) {
		for (int i = 0; i < Comprador.compradores.size(); i++) {
			Comprador local = Comprador.compradores.get(i);
			if (local == comprador) {
				return true;
			}
		}
		return false;
	}
	
	public static Comprador getComprador(String idComprador) {
		for (int i = 0; i < Comprador.compradores.size(); i++) {
			Comprador local = Comprador.compradores.get(i);
			if (local.getIdUsuario().equals(idComprador)) {
				return local;
			}
		}
		return null;
	}
	
	public void rechazarOferta(Oferta oferta) {
		this.ofertasPendientes.remove(oferta);
		this.ofertasRechazadas.add(oferta);
	}
}