package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de TipoCategoria.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez.
 */
public interface VOTipoCategoria 
{
	
	// -----------------------------------------------------------------
    // M�todos.
    // -----------------------------------------------------------------
	
	/**
	 * @return Nombre del tipo de la categoria.
	 */
	public String getNombreTipo();
	
	/**
	 * @return categoria del tipo.
	 */
	public String getNombreCategoria();
	
	/**
	 * Cadena de caracteres con todos los atributos del Tipo.
	 */
	@Override
	public String toString();
}
