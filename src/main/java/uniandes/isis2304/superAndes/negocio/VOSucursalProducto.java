package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de SucursalProducto.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOSucursalProducto {

	public String getDireccionSucursal();
	
	public String getCiudadSucursal();
	
	public String getProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos de SucursalProducto.
	 */
	@Override
	public String toString();
}
