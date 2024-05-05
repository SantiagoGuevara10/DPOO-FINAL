package galeria.usuarios;
import java.util.LinkedList;
import java.util.List;

public class UsuariosRegistrados{
	private List<Empleado> usuariosEnPrograma;
	
	public UsuariosRegistrados() {
		usuariosEnPrograma = new LinkedList<>();
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


	
}