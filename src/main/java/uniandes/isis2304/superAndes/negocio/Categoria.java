package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto de Categoria del producto.
 * @author Andr�s Hern�ndez
 */
public class Categoria implements VOCategoria{

    // -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * Nombre de la categoria.
	 */
	private String nombre;
	
    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public Categoria()
	{
		nombre = "";
	}

	/**
	 * Constructor con valores 
	 * @param nombre nombre de la categoria
	 * @param tipo tipo de la categoria.
	 */
	public Categoria(String nombre)
	{
		this.nombre = nombre;
	}
    // -----------------------------------------------------------------
    // M�todos.
    // -----------------------------------------------------------------
	
	/**
	 * @return nombre de la categor�a.
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * Asigna el nombre de la categoria.
	 * @param nombre nuevo nombre.
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	/**
	 * Cadena de caracteres con todos los atributos de la Categoria.
	 */
	@Override
	public String toString()
	{
		return "Categoria [nombre=" + nombre +"]";
	}	
}
