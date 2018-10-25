package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de TipoCategoria.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOTipoCategoria 
{
	
	// -----------------------------------------------------------------
    // Métodos.
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
