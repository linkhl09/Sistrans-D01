package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de TipoProducto.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOTipoProducto 
{
	
	// -----------------------------------------------------------------
    // Métodos.
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
