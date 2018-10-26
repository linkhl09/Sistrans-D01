package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los m�todos get de Categoria.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez.
 */
public interface VOCategoria {

	/**
	 * @return Nombre de la categor�a.
	 */
	public String getNombre();
	
	/**
	 * Cadena de caracteres con todos los atributos de Categoria.
	 */
	@Override
	public String toString();
}
