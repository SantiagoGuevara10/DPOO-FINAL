package galeria.inventarios;

import java.util.HashMap;
import java.util.Map;
import galeria.pieza.Pieza;
public class InventarioGeneral {
	
	
	private Map<String,Pieza> inventarioPasado;
	private Map<String,Pieza> inventarioBodega;
	private Map<String,Pieza> inventarioExhibido;
	
	
	public InventarioGeneral()
	{
		inventarioBodega = new HashMap<String, Pieza>( );
		inventarioPasado =  new HashMap<String, Pieza>( );
		inventarioExhibido = new HashMap<String, Pieza>( );
		
	}
	
	
	public Map<String,Pieza> getInventarioPasado(){
		return inventarioPasado;
	}
	public Map<String,Pieza> getInventarioBodega(){
		return inventarioBodega;
	}
	
	public Map<String,Pieza> getInventarioExhibido(){
		return inventarioExhibido;
	}
	
	public Pieza getPiezaInventarioPasado(String piezaId){
		return this.inventarioPasado.get(piezaId);
	}
	public Pieza getPiezaInventarioBodega(String piezaId){
		return this.inventarioBodega.get(piezaId);
	}
	
	public Pieza getPiezaInventarioExhibido(String piezaId){
		return this.inventarioExhibido.get(piezaId);
	}
	
	public boolean existeInventarioPasado(String piezaId) {
		return this.inventarioPasado.containsKey(piezaId);
	}
	
	public boolean existeInventarioBodega(String piezaId) {
		return this.inventarioBodega.containsKey(piezaId);
	}
	
	public boolean existeInventarioExhibido(String piezaId) {
		return this.inventarioExhibido.containsKey(piezaId);
	}
	
	public void addInventarioPasado(String piezaId, Pieza pieza) {
		this.inventarioPasado.put(piezaId, pieza);
	}
		
	public void addInventarioBodega(String piezaId, Pieza pieza) {
		this.inventarioBodega.put(piezaId, pieza);
	}
	
	public void addInventarioExhibido(String piezaId, Pieza pieza) {
		this.inventarioExhibido.put(piezaId, pieza);
	}
	
	public void removeInventarioPasado(String piezaId) {
		this.inventarioPasado.remove(piezaId);
	}
	
	public void removeInventarioBodega(String piezaId) {
		this.inventarioBodega.remove(piezaId);
	}
	
	public void removeInventarioExhibido(String piezaId) {
		this.inventarioExhibido.remove(piezaId);
	}
	
	

}