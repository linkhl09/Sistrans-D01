package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de la bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOBodega {

	/**
	 * @return id único de la bodega.
	 */
	public long getId();
	
	/**
	 * @return capacidad en peso de la bodega.
	 */
	public double getCapacidadPeso();
	
	/**
	 * @return capacidad en volumen de la bodega.
	 */
	public double getCapacidadVolumen();
	
	/**
	 * @return Tipo de productos que almacena la bodega.
	 */
	public String getTipo();
	
	/**
	 * @return dirección de la sucursal de la bodega.
	 */
	public Long getIdSucursal();
	
	/**
	 * Cadena de caracteres con todos los atributos de la bodega.
	 */
	@Override
	public String toString();
}
