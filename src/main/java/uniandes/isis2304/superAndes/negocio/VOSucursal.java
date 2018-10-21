package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de Sucursal.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOSucursal {

	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	/**
	 * @return El id de la sucursal.
	 */
	public long getId();
	
	/**
	 * @return La dirección de la sucursal.
	 */
	public String getDireccion();
	
	/**
	 * @return La ciudad de la sucursal.
	 */
	public String getCiudad();
	
	/**
	 * @return El nombre de la sucursal.
	 */
	public String getNombre();
	
	/**
	 * @return la segmentación de mercado de la sucursal.
	 */
	public String getSegmentacionMercado();
	
	/**
	 * @return el tamaño de la sucursal.
	 */
	public int getTamanio();
	
	/**
	 * Cadena de caracteres con todos los atributos de Sucursal.
	 */
	@Override
	public String toString();
}
