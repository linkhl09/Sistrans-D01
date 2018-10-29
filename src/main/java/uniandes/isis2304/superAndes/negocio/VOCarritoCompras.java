package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de Carrito de comrpas.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez y Jenifer Rodriguez.
 */
public interface VOCarritoCompras {
	
	/**
	 * @return El identificador del carrito de compras.
	 */
	public long getId();
	
	/**
	 * @return El identificador del cliente due�o del carrito de compras.
	 */
	public String getCliente();
	
	/**
	 * Cadena de caracteres con todos los atributos de la CarritoCompras.
	 */
	@Override
	public String toString();
}
