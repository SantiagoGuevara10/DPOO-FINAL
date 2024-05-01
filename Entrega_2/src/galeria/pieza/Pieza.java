package galeria.pieza;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import galeria.usuarios.Propietario;  

/**
 * Clase que representa una pieza de arte en la galería.
 */
public class Pieza {
    private String idPieza;
    private String titulo;
    private int anioCreacion;
    private String lugarCreacion;
    private String estadoPieza;
    private boolean estaExhibida;
    private boolean disponibleVenta;  
    private List<String> autores;
    private double valorFijo;
    private int valorMinimo;
    private int valorInicial;
    private Date fechaDeIngreso;
    private boolean esVigente;
    private String descripcion;
    private Propietario propietario;  

    public Pieza(String idPieza, String titulo, int anioCreacion, String lugarCreacion, 
                 String estadoPieza, boolean estaExhibida, boolean disponibleVenta, 
                 List<String> autores, double valorFijo, int valorMinimo, int valorInicial,
                 Date fechaDeIngreso, boolean esVigente, String descripcion, Propietario propietario) {
        this.idPieza = idPieza;
        this.titulo = titulo;
        this.anioCreacion = anioCreacion;
        this.lugarCreacion = lugarCreacion;
        this.estadoPieza = estadoPieza;
        this.estaExhibida = estaExhibida;
        this.disponibleVenta = disponibleVenta;
        this.autores = autores;
        this.valorFijo = valorFijo;
        this.valorMinimo = valorMinimo;
        this.valorInicial = valorInicial;
        this.fechaDeIngreso = fechaDeIngreso;
        this.esVigente = esVigente;
        this.descripcion = descripcion;
        this.propietario = propietario;
    }

  
    public String getIdPieza() {
        return idPieza;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public String getLugarCreacion() {
        return lugarCreacion;
    }

    public String getEstadoPieza() {
        return estadoPieza;
    }

    public boolean isEstaExhibida() {
        return estaExhibida;
    }

    public boolean isDisponibleVenta() {
        return disponibleVenta;
    }

    public List<String> getAutor() {
        return autores;
    }

    public double getValorFijo() {
        return valorFijo;
    }

    public int getValorMinimo() {
        return valorMinimo;
    }

    public int getValorInicial() {
        return valorInicial;
    }

    public Date getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public boolean isEsVigente() {
        return esVigente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Propietario getPropietario() {
        return propietario;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setAnioCreacion(int anioCreacion) {
        if (anioCreacion > 1000 && anioCreacion <= Calendar.getInstance().get(Calendar.YEAR)) {
            this.anioCreacion = anioCreacion;
        } else {
            throw new IllegalArgumentException("Año de creación inválido.");
        }
    }

    public void actualizarEstadoExhibicion(boolean estaExhibida) {
        this.estaExhibida = estaExhibida;
        System.out.println("Estado de exhibición actualizado para la pieza: " + titulo);
    }
}