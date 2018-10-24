package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de .
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOProductoOrdenPedido {

	/**
	 * @return identificador del pedido.
	 */
	public long getPedido();
	
	/**
	 * @return cantidad del producto asociado a la orden.
	 */
	public int getCantidad();
	
	/**
	 * @return calidad de los productos entregados.
	 */
	public double getCalidad();
	
	/**
	 * @return codigo del producto asociado a la orden.
	 */
	public String getProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos de ProductoOrdenPedido.
	 */
	@Override
	public String toString();
}
