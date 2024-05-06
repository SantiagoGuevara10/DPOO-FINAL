package galeria.usuarios;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import galeria.pieza.Pieza;
import subasta.Oferta;

public class UsuariosRegistrados{
	private List<Empleado> usuariosEnPrograma;
	private List<CompradorPropietario> compradoresEnPrograma;
	
	public UsuariosRegistrados(List<Empleado> usuariosEnPrograma,List<CompradorPropietario> compradoresEnPrograma ) {
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
	
	 public void guardarUsuarios( File archivo ) throws IOException
	    {
	        PrintWriter writer = new PrintWriter( archivo );

	        writer.println("Información de los empleados");

	        // Guardar la información de los tipos de usuarios
	        // tipodeusuario:nombre:id:username:password:(ofertas si es admin, ofertas registradas si es operador y transacciones si es cajero)
	        for(int i=0;i<usuariosEnPrograma.size();i++ )
	        {
	        	Empleado empleado = usuariosEnPrograma.get(i);
	        	String role = empleado.getRole();
	        	String nombre = empleado.getNombre();
	        	String username = empleado.getUsername();
	        	String password = empleado.getPasswordHash();
	        	String id = empleado.getIdEmpleado();
	        	
	        	if (role == "Administrador") {
	        		Administrador admin = (Administrador) usuariosEnPrograma.get(i);
	        		
	        		writer.println( "tipo:" + role + ":" + nombre + ":" + id + ":" + username + ":" + password);
	        		
	        	}
	        	else if (role == "Operador") {
	        		Operador operador = (Operador) usuariosEnPrograma.get(i);
	        		
	        		writer.println( "tipo:" + role + ":" + nombre + ":" + id + ":" + username + ":" + password);
	      
	        	}
	        	else if (role == "Cajero") {
	        		Cajero cajero = (Cajero) usuariosEnPrograma.get(i);
	        		
	        		writer.println( "tipo:" + role + ":" + nombre + ":" + id + ":" + username + ":" + password);
	        	}
	        	
	        	
	            
	        }
	        writer.println("\n");
	        writer.println("Información de los compradores");
	       // idusuario:nombre:username:password:informacioncontacto:piezas:piezasfavoritas:dinero:estaverificado
	        for(int i=0;i<compradoresEnPrograma.size();i++ )
	        {
	        	CompradorPropietario comprador = compradoresEnPrograma.get(i);
	        	String idUsuario = comprador.getIdUsuario();
	        	String nombre = comprador.getNombre();
	        	String username = comprador.getUsername();
	            String password = comprador.getPasswordHash();
	        	String informacionContacto = comprador.getInformacionContacto();
	        	List<Pieza> piezas = comprador.getPiezas();
	        	Set<Pieza> piezasFavoritas = comprador.getPiezasFavoritas();
	        	double dinero = comprador.getDinero();
	        	boolean estaVerificado = comprador.isEstaVerificado();
	        	
	        	
	            writer.println("Comprador"+idUsuario+":"+nombre+":"+username+":"+password+":"+informacionContacto+":"+piezas+":"+piezasFavoritas+":"+dinero+":"+estaVerificado)
	            ;
	        }

	        
	        writer.close( );
	    }
}