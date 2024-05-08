package galeria.usuarios;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import galeria.inventarios.PiezaEscultura;
import galeria.inventarios.PiezaFotografia;
import galeria.inventarios.PiezaPintura;
import galeria.inventarios.PiezaVideo;
import galeria.pieza.Pieza;
import subasta.Oferta;

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
	
	
	public void setcompradores(List<CompradorPropietario> lista) {
		this.compradoresEnPrograma = lista;
	}
	
	public void setempleados(List<Empleado> lista) {
		this.usuariosEnPrograma = lista;
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
	        	
	        	if (role.equals("Administrador")) {
	        		writer.println( "tipo:" + role + ":" + nombre + ":" + id + ":" + username + ":" + password);
	        
	        	}
	        	else if (role.equals("Operador")) {
	        		Operador operador = (Operador) usuariosEnPrograma.get(i);
	        		Map<String, Double> mapa = operador.getOfertasRegistradas();
	        		writer.println( "tipo:" + role + ":" + nombre + ":" + id + ":" + username + ":" + password);
	        		for (Map.Entry<String, Double> entry : mapa.entrySet()) {
	        		    String clave = entry.getKey();
	        		    Double valor = entry.getValue();
	        		    writer.println("ofertas"+":"+id+":"+clave+":"+valor);
	        		    
	        		}
	        	}
	        	else if (role.equals("Cajero")) {
	        		Cajero cajero = (Cajero) usuariosEnPrograma.get(i);
	        		writer.println( "tipo:" + role + ":" + nombre + ":" + id + ":" + username + ":" + password);
	        		List<String> transacciones = cajero.getTransacciones();
	        		if (transacciones!=null) {
	        			for(int j=0; i<transacciones.size();i++) {
		        			String transaccion = transacciones.get(j);
		        		    writer.println("transacciones"+":"+id+":"+transaccion);	
		        		}
	        			
	        			
	        		}
	        		
	        		
	        	}
	        	

	            
	        }
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
	        	
	        	
	            writer.println("Comprador"+":"+idUsuario+":"+nombre+":"+username+":"+password+":"+informacionContacto+":"+dinero+":"+estaVerificado)
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
	                
	                if(descripcion.equals("Escultura")) {
	                	PiezaEscultura escultura = (PiezaEscultura) piezas.get(j);
	                	float peso = escultura.getPeso();
	                    boolean usaElectricidad = escultura.getUsaElectricidad();
		                writer.println("Escultura"+":"+idUsuario+":"+idPieza+":"+titulo+":"+anioCreacion+":"+lugarCreacion+":"+estadoPieza+":"+estaExhibida+":"+disponibleVenta+":"+valorFijo+":"+valorMinimo+":"+valorInicial+":"+fechaString+":"+esVigente+":"+descripcion+":"+peso+":"+usaElectricidad);

	                }
	                else if(descripcion.equals("Fotografia")) {
	                	PiezaFotografia fotografia = (PiezaFotografia) piezas.get(j);
	                    boolean esDigital = fotografia.getEsDigital();
                        writer.println("Fotografia"+":"+idUsuario+":"+idPieza+":"+titulo+":"+anioCreacion+":"+lugarCreacion+":"+estadoPieza+":"+estaExhibida+":"+disponibleVenta+":"+valorFijo+":"+valorMinimo+":"+valorInicial+":"+fechaString+":"+esVigente+":"+descripcion+":"+esDigital);

	                	
	                }
                    else if(descripcion.equals("Pintura")) {
                    	PiezaPintura pintura = (PiezaPintura) piezas.get(j);
                    	int peso = pintura.getPeso();
                        String tecnica = pintura.getTecnica();

                        writer.println("Pintura"+":"+idUsuario+":"+idPieza+":"+titulo+":"+anioCreacion+":"+lugarCreacion+":"+estadoPieza+":"+estaExhibida+":"+disponibleVenta+":"+valorFijo+":"+valorMinimo+":"+valorInicial+":"+fechaString+":"+esVigente+":"+descripcion+":"+peso+":"+tecnica);

	                	
	                }
                     else if(descripcion.equals("Video")) {
                     	PiezaVideo video = (PiezaVideo) piezas.get(j);
                        String calidad = video.getCalidad();
                    	int duracion = video.getDuracion();

                        writer.println("Video"+":"+idUsuario+":"+idPieza+":"+titulo+":"+anioCreacion+":"+lugarCreacion+":"+estadoPieza+":"+estaExhibida+":"+disponibleVenta+":"+valorFijo+":"+valorMinimo+":"+valorInicial+":"+fechaString+":"+esVigente+":"+descripcion+":"+calidad+":"+duracion);
     	                	
	                	
	                }
	                	
	                
	                for(int k=0; k<autores.size();k++) {
	                	String autor = autores.get(k);
	                	writer.println("Autor"+":"+idUsuario+":"+idPieza+":"+autor);

	                }
	            	
	            	
	            }
	           
	            for(int j=0; j<piezasFavoritas.size();j++) {
	            	Pieza pieza = piezasFavoritas.get(j);
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
	                
	                if(descripcion.equals("Escultura")) {
	                	PiezaEscultura escultura = (PiezaEscultura) piezasFavoritas.get(j);
	                	float peso = escultura.getPeso();
	                    boolean usaElectricidad = escultura.getUsaElectricidad();
		                writer.println("EsculturaFav"+":"+idUsuario+":"+idPieza+":"+titulo+":"+anioCreacion+":"+lugarCreacion+":"+estadoPieza+":"+estaExhibida+":"+disponibleVenta+":"+valorFijo+":"+valorMinimo+":"+valorInicial+":"+fechaString+":"+esVigente+":"+descripcion+":"+peso+":"+usaElectricidad);

	                }
	                else if(descripcion.equals("Fotografia")) {
	                	PiezaFotografia fotografia = (PiezaFotografia) piezasFavoritas.get(j);
	                    boolean esDigital = fotografia.getEsDigital();
                        writer.println("FotografiaFav"+":"+idUsuario+":"+idPieza+":"+titulo+":"+anioCreacion+":"+lugarCreacion+":"+estadoPieza+":"+estaExhibida+":"+disponibleVenta+":"+valorFijo+":"+valorMinimo+":"+valorInicial+":"+fechaString+":"+esVigente+":"+descripcion+":"+esDigital);

	                	
	                }
                    else if(descripcion.equals("Pintura")) {
                    	PiezaPintura pintura = (PiezaPintura) piezasFavoritas.get(j);
                    	int peso = pintura.getPeso();
                        String tecnica = pintura.getTecnica();

                        writer.println("PinturaFav"+":"+idUsuario+":"+idPieza+":"+titulo+":"+anioCreacion+":"+lugarCreacion+":"+estadoPieza+":"+estaExhibida+":"+disponibleVenta+":"+valorFijo+":"+valorMinimo+":"+valorInicial+":"+fechaString+":"+esVigente+":"+descripcion+":"+peso+":"+tecnica);

	                	
	                }
                     else if(descripcion.equals("Video")) {
                     	PiezaVideo video = (PiezaVideo) piezasFavoritas.get(j);
                        String calidad = video.getCalidad();
                    	int duracion = video.getDuracion();

                        writer.println("VideoFav"+":"+idUsuario+":"+idPieza+":"+titulo+":"+anioCreacion+":"+lugarCreacion+":"+estadoPieza+":"+estaExhibida+":"+disponibleVenta+":"+valorFijo+":"+valorMinimo+":"+valorInicial+":"+fechaString+":"+esVigente+":"+descripcion+":"+calidad+":"+duracion);
     	                	
	                	
	                }

	                	                
	                for(int k=0; k<autores.size();k++) {
	                	String autor = autores.get(k);
	                	writer.println("AutorFav"+":"+idUsuario+":"+idPieza+":"+autor);
	                }
	            	
	            	
	            }
	            
	            
	        }


	        
	        writer.close( );
	    }
	 
	 
	 
 public static UsuariosRegistrados cargarEstado( File archivo ) throws FileNotFoundException, IOException, NumberFormatException, ParseException
	    {
	    List<Empleado>usuariosEnPrograma = new LinkedList<>();
	    List<CompradorPropietario> compradoresEnPrograma = new LinkedList<>();

	        BufferedReader br = new BufferedReader( new FileReader( archivo ) );
	        String line = br.readLine( );
	        while( line != null )
	        {
	            String[] partes = line.split( ":" );
	            if( partes[ 0 ].equals( "tipo" ) )
	            {
	                String role = partes[ 1 ];
	                String nombre = partes[ 2 ];
	                String username = partes[ 4 ];
	                String password = partes[ 5 ];
	                String id = partes[ 3 ];
	                if (role.equals("Operador")) {
	                	Operador operador = new Operador(id,nombre, username, password, role);
	                	usuariosEnPrograma.add(operador);
	                	
	                	
	                
		        	}
		        	else if (role.equals("Cajero")) {
		        		Cajero cajero = new Cajero(id,nombre, username, password, role);
		        		usuariosEnPrograma.add(cajero);
		        		
		        		
		        		
		        		}
		        	else {
		        		Administrador admin = new Administrador(id,nombre, username, password, role);
		        		usuariosEnPrograma.add(admin);
		        	}
	          
	                
		        	}
	            else if (partes[ 0 ].equals("transacciones")) {
	                String id = partes[ 1 ];
	                String transaccion = partes[ 2 ];
	                
	                for(int m=0; m<usuariosEnPrograma.size();m++) {
	                	Empleado empleado = usuariosEnPrograma.get(m);
	                	if (empleado.getRole().equals("Cajero") && empleado.getIdEmpleado().equals(id)) {
	    	        		Cajero cajero = (Cajero) usuariosEnPrograma.get(m);
	    	        		cajero.setTransacciones(transaccion);
	                	}	
	                }}
	            
	            else if (partes[ 0 ].equals("ofertas")) {
	                String id = partes[ 1 ];
	                String key = partes[ 2 ];
	                double valor = Double.parseDouble( partes[ 3 ] );

	                
	                for(int m=0; m<usuariosEnPrograma.size();m++) {
	                	Empleado empleado = usuariosEnPrograma.get(m);
	                	if (empleado.getRole().equals("Operador") && empleado.getIdEmpleado().equals(id)) {
	    	        		Operador operador = (Operador) usuariosEnPrograma.get(m);
	    	        		operador.getOfertasRegistradas().put(key, valor);
	    	        		}} 
	           
	            }
	            
	            
	            else if (partes[ 0 ].equals("Comprador")) {
	                String id = partes[ 1 ];
	                String nombre = partes[2];
	                String username = partes[3];
	                String password = partes[4];
	                String info = partes[5];
	                double dinero = Double.parseDouble(partes[6]);
	                boolean estaVerificado = Boolean.parseBoolean(partes[7]);
	        	    List<Pieza> piezas = new LinkedList<>();
	        	    List<Pieza> piezasFavoritas = new LinkedList<>();
 
	                CompradorPropietario comprador = new CompradorPropietario(id, nombre, username, password, info, dinero, estaVerificado, piezas, piezasFavoritas);
	                compradoresEnPrograma.add(comprador);
	            }
	            
	            else if(partes[0].equals("Escultura")) {
	            	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	Date fecha = formato.parse(partes[12]);
	            	String id= partes[1];
	            	String idPieza = partes[2];
	                String titulo =  partes[3];
	                int anioCreacion = Integer.parseInt(partes[4]);
	                String lugarCreacion = partes[5];
	                String estadoPieza = partes[6];
	                boolean estaExhibida = Boolean.parseBoolean(partes[7]);
	                boolean disponibleVenta = Boolean.parseBoolean(partes[8]);
	                List<String> autores = new LinkedList<>();
	                double valorFijo = Double.parseDouble(partes[9]);
	                int valorMinimo = Integer.parseInt(partes[10]);
	                int valorInicial = Integer.parseInt(partes[11]);
	                boolean esVigente = Boolean.parseBoolean(partes[13]);
	                String descripcion = partes[14];
	                int peso = Integer.parseInt(partes[15]);
	                boolean usaElectricidad = Boolean.parseBoolean(partes[16]);
	                PiezaEscultura pieza = new PiezaEscultura(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, peso, usaElectricidad);

	                for(int m=0; m<compradoresEnPrograma.size();m++) {
	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
	                	if (comprador.getIdUsuario().equals(id) ) {
	                		comprador.getPiezas().add(pieza);
	    	        		}}
	            	
	            }
                else if(partes[0].equals("Fotografia")) {
                	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	Date fecha = formato.parse(partes[12]);
	            	String id= partes[1];
	            	String idPieza = partes[2];
	                String titulo =  partes[3];
	                int anioCreacion = Integer.parseInt(partes[4]);
	                String lugarCreacion = partes[5];
	                String estadoPieza = partes[6];
	                boolean estaExhibida = Boolean.parseBoolean(partes[7]);
	                boolean disponibleVenta = Boolean.parseBoolean(partes[8]);
	                List<String> autores = new LinkedList<>();
	                double valorFijo = Double.parseDouble(partes[9]);
	                int valorMinimo = Integer.parseInt(partes[10]);
	                int valorInicial = Integer.parseInt(partes[11]);
	                boolean esVigente = Boolean.parseBoolean(partes[13]);
	                String descripcion = partes[14];
	                boolean esDigital = Boolean.parseBoolean(partes[15]);
	                PiezaFotografia pieza = new PiezaFotografia(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, esDigital);

	                for(int m=0; m<compradoresEnPrograma.size();m++) {
	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
	                	if (comprador.getIdUsuario().equals(id) ) {
	                		comprador.getPiezas().add(pieza);
	    	        		}}
                	
                	
                }
                else if(partes[0].equals("Pintura")) {
                	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	Date fecha = formato.parse(partes[12]);
	            	String id= partes[1];
	            	String idPieza = partes[2];
	                String titulo =  partes[3];
	                int anioCreacion = Integer.parseInt(partes[4]);
	                String lugarCreacion = partes[5];
	                String estadoPieza = partes[6];
	                boolean estaExhibida = Boolean.parseBoolean(partes[7]);
	                boolean disponibleVenta = Boolean.parseBoolean(partes[8]);
	                List<String> autores = new LinkedList<>();
	                double valorFijo = Double.parseDouble(partes[9]);
	                int valorMinimo = Integer.parseInt(partes[10]);
	                int valorInicial = Integer.parseInt(partes[11]);
	                boolean esVigente = Boolean.parseBoolean(partes[13]);
	                String descripcion = partes[14];
	                int peso = Integer.parseInt(partes[15]);
	                String tecnica = partes[16];
	                PiezaPintura pieza = new PiezaPintura(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, peso, tecnica);

	                for(int m=0; m<compradoresEnPrograma.size();m++) {
	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
	                	if (comprador.getIdUsuario().equals(id) ) {
	                		comprador.getPiezas().add(pieza);
	    	        		}}
                	
                	
                }
                 else if(partes[0].equals("Video")) {
                 	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	            	Date fecha = formato.parse(partes[12]);
 	            	String id= partes[1];
 	            	String idPieza = partes[2];
 	                String titulo =  partes[3];
 	                int anioCreacion = Integer.parseInt(partes[4]);
 	                String lugarCreacion = partes[5];
 	                String estadoPieza = partes[6];
 	                boolean estaExhibida = Boolean.parseBoolean(partes[7]);
 	                boolean disponibleVenta = Boolean.parseBoolean(partes[8]);
 	                List<String> autores = new LinkedList<>();
 	                double valorFijo = Double.parseDouble(partes[9]);
 	                int valorMinimo = Integer.parseInt(partes[10]);
 	                int valorInicial = Integer.parseInt(partes[11]);
 	                boolean esVigente = Boolean.parseBoolean(partes[13]);
 	                String descripcion = partes[14];
 	                String calidad = partes[15];
 	                int duracion = Integer.parseInt(partes[16]);
 	                PiezaVideo pieza = new PiezaVideo(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, calidad, duracion);

 	                for(int m=0; m<compradoresEnPrograma.size();m++) {
 	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
 	                	if (comprador.getIdUsuario().equals(id) ) {
 	                		comprador.getPiezas().add(pieza);
 	    	        		}}
                 	
                 	
                 
                	 
                	 
                 }
	            
	            else if (partes[ 0 ].equals("Autor")) {
	            	String idUsuario = partes[1];
	            	String idPieza = partes[2];
	            	String autor = partes[3];
	            	for(int m=0; m<compradoresEnPrograma.size();m++) {
	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
	                	if (comprador.getIdUsuario().equals(idUsuario)) {
	                		List<Pieza> piezas = comprador.getPiezas();
	                		for(int l=0; l<piezas.size();l++) {
	                			if (piezas.get(l).getIdPieza().equals(idPieza)) {
	                				piezas.get(l).setAutores(autor);
	                			}
	                		}
	                		
	    	        		}}
	            	
	            	
	            }
	            
	            else if(partes[0].equals("EsculturaFav")) {
	            	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	Date fecha = formato.parse(partes[12]);
	            	String id= partes[1];
	            	String idPieza = partes[2];
	                String titulo =  partes[3];
	                int anioCreacion = Integer.parseInt(partes[4]);
	                String lugarCreacion = partes[5];
	                String estadoPieza = partes[6];
	                boolean estaExhibida = Boolean.parseBoolean(partes[7]);
	                boolean disponibleVenta = Boolean.parseBoolean(partes[8]);
	                List<String> autores = new LinkedList<>();
	                double valorFijo = Double.parseDouble(partes[9]);
	                int valorMinimo = Integer.parseInt(partes[10]);
	                int valorInicial = Integer.parseInt(partes[11]);
	                boolean esVigente = Boolean.parseBoolean(partes[13]);
	                String descripcion = partes[14];
	                int peso = Integer.parseInt(partes[15]);
	                boolean usaElectricidad = Boolean.parseBoolean(partes[16]);
	                PiezaEscultura pieza = new PiezaEscultura(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, peso, usaElectricidad);

	                for(int m=0; m<compradoresEnPrograma.size();m++) {
 	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
 	                	if (comprador.getIdUsuario().equals(id) ) {
 	                		comprador.getPiezasFvoritas().add(pieza);
	            	
	            }}}
                else if(partes[0].equals("FotografiaFav")) {
                	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	Date fecha = formato.parse(partes[12]);
	            	String id= partes[1];
	            	String idPieza = partes[2];
	                String titulo =  partes[3];
	                int anioCreacion = Integer.parseInt(partes[4]);
	                String lugarCreacion = partes[5];
	                String estadoPieza = partes[6];
	                boolean estaExhibida = Boolean.parseBoolean(partes[7]);
	                boolean disponibleVenta = Boolean.parseBoolean(partes[8]);
	                List<String> autores = new LinkedList<>();
	                double valorFijo = Double.parseDouble(partes[9]);
	                int valorMinimo = Integer.parseInt(partes[10]);
	                int valorInicial = Integer.parseInt(partes[11]);
	                boolean esVigente = Boolean.parseBoolean(partes[13]);
	                String descripcion = partes[14];
	                boolean esDigital = Boolean.parseBoolean(partes[15]);
	                PiezaFotografia pieza = new PiezaFotografia(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, esDigital);

	                for(int m=0; m<compradoresEnPrograma.size();m++) {
 	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
 	                	if (comprador.getIdUsuario().equals(id) ) {
 	                		comprador.getPiezasFvoritas().add(pieza);
                	
                	
 	                	}}}
                else if(partes[0].equals("PinturaFav")) {
                	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	Date fecha = formato.parse(partes[12]);
	            	String id= partes[1];
	            	String idPieza = partes[2];
	                String titulo =  partes[3];
	                int anioCreacion = Integer.parseInt(partes[4]);
	                String lugarCreacion = partes[5];
	                String estadoPieza = partes[6];
	                boolean estaExhibida = Boolean.parseBoolean(partes[7]);
	                boolean disponibleVenta = Boolean.parseBoolean(partes[8]);
	                List<String> autores = new LinkedList<>();
	                double valorFijo = Double.parseDouble(partes[9]);
	                int valorMinimo = Integer.parseInt(partes[10]);
	                int valorInicial = Integer.parseInt(partes[11]);
	                boolean esVigente = Boolean.parseBoolean(partes[13]);
	                String descripcion = partes[14];
	                int peso = Integer.parseInt(partes[15]);
	                String tecnica = partes[16];
	                PiezaPintura pieza = new PiezaPintura(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, peso, tecnica);

	                for(int m=0; m<compradoresEnPrograma.size();m++) {
 	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
 	                	if (comprador.getIdUsuario().equals(id) ) {
 	                		comprador.getPiezasFvoritas().add(pieza);
                	
                	
 	                	}}}
                 else if(partes[0].equals("VideoFav")) {
                 	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	            	Date fecha = formato.parse(partes[12]);
 	            	String id= partes[1];
 	            	String idPieza = partes[2];
 	                String titulo =  partes[3];
 	                int anioCreacion = Integer.parseInt(partes[4]);
 	                String lugarCreacion = partes[5];
 	                String estadoPieza = partes[6];
 	                boolean estaExhibida = Boolean.parseBoolean(partes[7]);
 	                boolean disponibleVenta = Boolean.parseBoolean(partes[8]);
 	                List<String> autores = new LinkedList<>();
 	                double valorFijo = Double.parseDouble(partes[9]);
 	                int valorMinimo = Integer.parseInt(partes[10]);
 	                int valorInicial = Integer.parseInt(partes[11]);
 	                boolean esVigente = Boolean.parseBoolean(partes[13]);
 	                String descripcion = partes[14];
 	                String calidad = partes[15];
 	                int duracion = Integer.parseInt(partes[16]);
 	                PiezaVideo pieza = new PiezaVideo(idPieza, titulo, anioCreacion, lugarCreacion , estadoPieza, estaExhibida, disponibleVenta, autores, valorFijo, valorMinimo, valorInicial, fecha,esVigente, descripcion, calidad, duracion);

 	                for(int m=0; m<compradoresEnPrograma.size();m++) {
 	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
 	                	if (comprador.getIdUsuario().equals(id) ) {
 	                		comprador.getPiezasFvoritas().add(pieza);
 	    	        
 	                	}}
                 	
                 
                	 
                	 
                 }
	            
	            
	                  
	            
	            else if (partes[ 0 ].equals("AutorFav")) {
	            	String idUsuario = partes[1];
	            	String idPieza = partes[2];
	            	String autor = partes[3];
	            	for(int m=0; m<compradoresEnPrograma.size();m++) {
	                	CompradorPropietario comprador = compradoresEnPrograma.get(m);
	                	if (comprador.getIdUsuario()==idUsuario  ) {
	                		List<Pieza> piezasFavoritas = comprador.getPiezasFvoritas();
	                		for(int l=0; l<piezasFavoritas.size();l++) {
	                			if (piezasFavoritas.get(l).getIdPieza()==idPieza) {
	                				piezasFavoritas.get(l).setAutores(autor);
	                			}
	                		}
	                		
	    	        		}}
	            	
	            	
	            }
	            line = br.readLine( );	
	            }        br.close( );

	            
	            UsuariosRegistrados usuariosprograma = new UsuariosRegistrados();
	            usuariosprograma.setcompradores(compradoresEnPrograma);
	            usuariosprograma.setempleados(usuariosEnPrograma);
	            
	            
	           return usuariosprograma; 
	    }

	}

