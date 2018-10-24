package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de ProveedoresProducto.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOProveedoresProducto {

	/**
	 * @return identificacion de proveedor.
	 */
	public String getProveedor();
	
	/**
	 * @return codigo del producto.
	 */
	public String getProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos de ProveedoresProducto.
	 */
	@Override
	public String toString();	
}
