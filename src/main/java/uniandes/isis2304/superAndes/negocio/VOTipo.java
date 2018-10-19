package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de .
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOTipo {

	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return nombre del tipo.
	 */
	public String getNombre();
	
	/**
	 * @return categoria del tipo.
	 */
	public String getCategoria();
	
	/**
	 * Cadena de caracteres con todos los atributos del Tipo.
	 */
	@Override
	public String toString();
}
