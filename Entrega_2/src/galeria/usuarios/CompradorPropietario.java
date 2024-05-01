package galeria.usuarios;

import galeria.pieza.Pieza;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompradorPropietario {
    protected String idUsuario;
    protected String nombre;
    protected String username;
    protected String informacionContacto;
    protected List<Pieza> piezas;
    protected Set<Pieza> piezasFavoritas;
    protected double limiteCompra;
    protected boolean estaVerificado;

    
    public CompradorPropietario(String idUsuario, String nombre, String username,
                                String informacionContacto, double limiteCompra,
                                boolean estaVerificado, List<Pieza> piezas, Set<Pieza> piezasFavoritas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.informacionContacto = informacionContacto;
        this.limiteCompra = limiteCompra;
        this.estaVerificado = estaVerificado;
        this.piezas = piezas != null ? new ArrayList<>(piezas) : new ArrayList<>();
        this.piezasFavoritas = piezasFavoritas != null ? new HashSet<>(piezasFavoritas) : new HashSet<>();
    }


    public CompradorPropietario(String idUsuario, String nombre, String username,
                                String informacionContacto, double limiteCompra,
                                boolean estaVerificado) {
        this(idUsuario, nombre, username, informacionContacto, limiteCompra,
             estaVerificado, null, null);
    }


    public CompradorPropietario(String idUsuario) {
        this(idUsuario, "Default Name", "Default Username", "Default Contact", 0.0, false, new ArrayList<>(), new HashSet<>());
    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public String getInformacionContacto() {
        return informacionContacto;
    }

    public double getLimiteCompra() {
        return limiteCompra;
    }

    public boolean isEstaVerificado() {
        return estaVerificado;
    }

    public List<Pieza> getPiezas() {
        return new ArrayList<>(piezas);
    }


    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setInformacionContacto(String informacionContacto) {
        this.informacionContacto = informacionContacto;
    }

    public void setLimiteCompra(double limiteCompra) {
        this.limiteCompra = limiteCompra;
    }

    public void setEstaVerificado(boolean estaVerificado) {
        this.estaVerificado = estaVerificado;
    }


    public void addPieza(Pieza pieza) {
        if (!estaVerificado) {
            
            return;
        }
        
        this.piezas.add(pieza);
       
    }

    public void removePieza(Pieza pieza) {
        if (piezas.remove(pieza)) {
            
        } else {
            
        }
    }

    public void toggleFavorite(Pieza pieza) {
        if (piezasFavoritas.contains(pieza)) {
            piezasFavoritas.remove(pieza);
           
        } else {
            piezasFavoritas.add(pieza);
            
        }
    }


    public void mostrarPiezas() {
        if (piezas.isEmpty()) {
            
        } else {
           
            for (Pieza pieza : piezas) {
              
            }
        }
    }

    public void mostrarFavoritos() {
        if (piezasFavoritas.isEmpty()) {
          
        } else {
           
            for (Pieza pieza : piezasFavoritas) {
             
            }
        }
    }
}