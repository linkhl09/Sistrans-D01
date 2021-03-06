package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de Tipo.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez.
 */
public interface VOTipo {

	// -----------------------------------------------------------------
    // M�todos.
    // -----------------------------------------------------------------

	/**
	 * @return Nombre del tipo.
	 */
	public String getNombre();
	
	/**
	 * Cadena de caracteres con todos los atributos del Tipo.
	 */
	@Override
	public String toString();
}
