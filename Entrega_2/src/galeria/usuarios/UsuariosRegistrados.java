package galeria.usuarios;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	        		writer.println( "tipo:" + role + ":" + nombre + ":" + id + ":" + username + ":" + password);
	        
	        	}
	        	else if (role == "Operador") {
	        		Operador operador = (Operador) usuariosEnPrograma.get(i);
	        		Map<String, Double> mapa = operador.getOfertasRegistradas();
	        		writer.println( "tipo:" + role + ":" + nombre + ":" + id + ":" + username + ":" + password);
	        		for (Map.Entry<String, Double> entry : mapa.entrySet()) {
	        		    String clave = entry.getKey();
	        		    Double valor = entry.getValue();
	        		    writer.println("ofertas"+":"+id+":"+clave+":"+valor);
	        		    
	        		}
	        	}
	        	else if (role == "Cajero") {
	        		Cajero cajero = (Cajero) usuariosEnPrograma.get(i);
	        		writer.println( "tipo:" + role + ":" + nombre + ":" + id + ":" + username + ":" + password);
	        		List<String> transacciones = cajero.getTransacciones();
	        		for(int j=0; i<transacciones.size();i++) {
	        			String transaccion = transacciones.get(j);
	        		    writer.println("ofertas"+":"+id+":"+transaccion);	
	        		}
	        		
	        	}
	        	
	        	
	            
	        }
	        writer.println("\n");
	        writer.println("Información de los compradores");
	       // idusuario:nombre:username:password:informacioncontacto:dinero:estaverificado
	        for(int i=0;i<compradoresEnPrograma.size();i++ )
	        {
	        	CompradorPropietario comprador = compradoresEnPrograma.get(i);
	        	String idUsuario = comprador.getIdUsuario();
	        	String nombre = comprador.getNombre();
	        	String username = comprador.getUsername();
	            String password = comprador.getPasswordHash();
	        	String informacionContacto = comprador.getInformacionContacto();
	        	List<Pieza> piezas = comprador.getPiezas();
	        	List<Pieza> piezasFavoritas = comprador.getPiezasFvoritas();
	        	double dinero = comprador.getDinero();
	        	boolean estaVerificado = comprador.isEstaVerificado();
	        	
	        	
	            writer.println("Comprador"+idUsuario+":"+nombre+":"+username+":"+password+":"+informacionContacto+":"+dinero+":"+estaVerificado)
	            ;
	            for(int j=0; j<piezas.size();j++) {
	            	Pieza pieza = piezas.get(j);
	                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	String idPieza = pieza.getIdPieza();
	                String titulo = pieza.getTitulo();
	                int anioCreacion = pieza.getAnioCreacion();
	                String lugarCreacion = pieza.getLugarCreacion();
	                String estadoPieza = pieza.getEstadoPieza();
	                boolean estaExhibida = pieza.getEstaExhibida();
	                boolean disponibleVenta = pieza.getDisponibleVenta(); 
	                List<String> autores = pieza.getAutor();
	                double valorFijo = pieza.getValorFijo();
	                int valorMinimo = pieza.getValorMinimo();
	                int valorInicial = pieza.getValorInicial();
	                String fechaString = formato.format(pieza.getFechaDeIngreso());
	                boolean esVigente = pieza.isEsVigente();
	                String descripcion = pieza.getDescripcion();
	                
	                writer.println("Pieza"+":"+idUsuario+":"+idPieza+":"+titulo+":"+anioCreacion+":"+lugarCreacion+":"+estadoPieza+":"+estaExhibida+":"+disponibleVenta+":"+valorFijo+":"+valorMinimo+":"+valorInicial+":"+fechaString+":"+esVigente+":"+descripcion);
	                
	                for(int k=0; k<autores.size();k++) {
	                	String autor = autores.get(k);
	                	writer.println("Autor"+":"+idPieza+":"+autor);
	                }
	            	
	            	
	            }
	        }

	        
	        writer.close( );
	    }
}