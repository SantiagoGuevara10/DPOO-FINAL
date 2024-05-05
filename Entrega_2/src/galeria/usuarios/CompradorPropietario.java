package galeria.usuarios;

import galeria.pieza.Pieza;
import java.util.List;
import java.util.Set;

public class CompradorPropietario {
    protected String idUsuario;
    protected String nombre;
    protected String username;
    protected String passwordHash;
    protected String informacionContacto;
    protected List<Pieza> piezas;
    protected Set<Pieza> piezasFavoritas;
    protected double dinero;
    protected boolean estaVerificado;

    
    public CompradorPropietario(String idUsuario, String nombre, String username,String passwordHash,
                                String informacionContacto, double dinero,
                                boolean estaVerificado, List<Pieza> piezas, Set<Pieza> piezasFavoritas) {
        
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.passwordHash = passwordHash;
        this.informacionContacto = informacionContacto;
        this.dinero = dinero;
        this.estaVerificado = estaVerificado;
        this.piezas = piezas;
        this.piezasFavoritas = piezasFavoritas;

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(Pieza pieza) {
    	this.piezas.add(pieza);
    }

    public Set<Pieza> getPiezasFavoritas() {
        return piezasFavoritas;
    }

    public void setPiezasFavoritas(Pieza pieza) {
    	this.piezasFavoritas.add(pieza);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getInformacionContacto() {
        return informacionContacto;
    }

    public void setInformacionContacto(String informacionContacto) {
        this.informacionContacto = informacionContacto;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public boolean isEstaVerificado() {
        return estaVerificado;
    }

    public void setEstaVerificado(boolean estaVerificado) {
        this.estaVerificado = estaVerificado;
    }


    
}