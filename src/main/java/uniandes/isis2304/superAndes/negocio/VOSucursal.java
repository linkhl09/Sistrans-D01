package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de Sucursal.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOSucursal {

	
	public String getDireccion();
	
	
	public String getCiudad();
	
	
	public String getNombre();
	
	
	public String getSegmentacionMercado();
	
	
	public int getTamanio();
	
	/**
	 * Cadena de caracteres con todos los atributos de Sucursal.
	 */
	@Override
	public String toString();
}
