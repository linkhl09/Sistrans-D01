package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de TipoProducto.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez.
 */
public interface VOTipoProducto 
{
	
	// -----------------------------------------------------------------
    // M�todos.
    // -----------------------------------------------------------------
	
	/**
	 * @return Nombre del tipo del producto.
	 */
	public String getNombreTipo();
	
	/**
	 * @return Identificador del producto..
	 */
	public String getCodigoBarrasProducto();
	
	/**
	 * Cadena de caracteres con todos los atributos del Tipo.
	 */
	@Override
	public String toString();
}
