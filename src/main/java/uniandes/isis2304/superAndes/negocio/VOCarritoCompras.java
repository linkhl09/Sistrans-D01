package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de Carrito de comrpas.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOCarritoCompras {
	
	/**
	 * @return El identificador del carrito de compras.
	 */
	public long getId();
	
	/**
	 * Cadena de caracteres con todos los atributos de la CarritoCompras.
	 */
	@Override
	public String toString();
}
