package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de Carrito de comrpas.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández y Jenifer Rodriguez.
 */
public interface VOCarritoCompras {
	
	/**
	 * @return El identificador del carrito de compras.
	 */
	public long getId();
	
	/**
	 * @return El identificador del cliente dueño del carrito de compras.
	 */
	public String getCliente();
	
	/**
	 * @return El identificador de la sucursal donde esta el carrito de compras.
	 */
	public long getIdSucursal();

	
	/**
	 * Cadena de caracteres con todos los atributos de la CarritoCompras.
	 */
	@Override
	public String toString();
}
