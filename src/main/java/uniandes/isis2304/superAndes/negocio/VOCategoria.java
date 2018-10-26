package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de Categoria.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOCategoria {

	/**
	 * @return Nombre de la categoría.
	 */
	public String getNombre();
	
	/**
	 * Cadena de caracteres con todos los atributos de Categoria.
	 */
	@Override
	public String toString();
}
