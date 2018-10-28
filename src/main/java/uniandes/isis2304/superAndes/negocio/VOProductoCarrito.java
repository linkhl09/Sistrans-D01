package uniandes.isis2304.superAndes.negocio;


/**
 * Interfaz para los m�todos get de PRODUCTOCARRITO.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz. 
 * 
 * @author Andr�s Hern�ndez
 */
public interface VOProductoCarrito 
{
	/**
	 * @return El identificador del carrito de compras.
	 */
	public long getCarrito();
	
	/**
	 * @return La cantidad de unidades del producto.
	 */
	public int getCantidad();
	
	/**
	 * @return El identificador del Producto puesto en el carrito de compras.
	 */
	public String getCodigoBarrasProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos de el ProductoCarrito.
	 */
	@Override
	public String toString();
}
