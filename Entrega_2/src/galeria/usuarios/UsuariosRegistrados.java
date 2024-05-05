package galeria.usuarios;
import java.util.LinkedList;
import java.util.List;

public class UsuariosRegistrados{
	private List<Empleado> usuariosEnPrograma;
	private List<CompradorPropietario> compradoresEnPrograma;
	
	public UsuariosRegistrados() {
		usuariosEnPrograma = new LinkedList<>();
		compradoresEnPrograma = new LinkedList<>();
	}
	
	public void addUsuario(Empleado empleado) {
		this.usuariosEnPrograma.add(empleado);
	}

	public void removeUsuario(Empleado empleado) {
		this.usuariosEnPrograma.remove(empleado);
	}

	public List<Empleado> getUsuariosEnPrograma() {
		return usuariosEnPrograma;
	}
	
	public void addComprador(CompradorPropietario comprador) {
		this.compradoresEnPrograma.add(comprador);
	}
	
	public void removeComprador(CompradorPropietario comprador) {
		this.compradoresEnPrograma.remove(comprador);
	}
	
	public List<CompradorPropietario> getCompradoresEnPrograma() {
		return compradoresEnPrograma;
	}
	
	
	
	
}