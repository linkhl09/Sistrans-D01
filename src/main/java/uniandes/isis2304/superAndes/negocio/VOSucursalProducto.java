package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de SucursalProducto.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez.
 */
public interface VOSucursalProducto 
{

	/**
	 * @return La id de la sucursal due�a del producto.
	 */
	public long getIdSucursal();
	
	/**
	 * @return El codigo de barras del producto asociado a la sucursal.
	 */
	public String getCodigoBarrasProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos de SucursalProducto.
	 */
	@Override
	public String toString();
}
