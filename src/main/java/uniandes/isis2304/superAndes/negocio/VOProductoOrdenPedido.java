package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de .
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOProductoOrdenPedido {

	public long getPedido();
	
	public int getCantidad();
	
	public double getCalidad();
	
	public String getProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos de ProductoOrdenPedido.
	 */
	@Override
	public String toString();
}
