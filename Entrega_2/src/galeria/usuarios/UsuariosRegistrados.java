package galeria.usuarios;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UsuariosRegistrados{
	private List<Empleado> usuariosEnPrograma;
	
	public UsuariosRegistrados() {
		usuariosEnPrograma = new LinkedList<>();
	}
	
	public void addUsuario(Empleado empleado) {
		this.usuariosEnPrograma.add(empleado);
		
	}
	
}