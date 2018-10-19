package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de Tipo del producto.
 * @author Andrés Hernández
 */
public class Tipo implements VOTipo
{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 * Nombre del tipo.
	 */
	private String nombre;
	
	private String categoria;

	// -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------
	
	/**
	 * Constructor vacio.
	 */
	public Tipo()
	{
		nombre="";
		categoria = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param nombre
	 */
	public Tipo(String nombre, String categoria)
	{
		this.nombre = nombre;
		this.categoria = categoria;
	}
	
    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------	
	
	/**
	 * @return nombre del tipo.
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * Set del nombre.
	 * @param nombre nuevo nombre del tipo.
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return categoria del tipo.
	 */
	public String getCategoria() 
	{
		return categoria;
	}

	/**
	 * Set de la categoria.
	 * @param categoria nueva categoria.
	 */
	public void setCategoria(String categoria) 
	{
		this.categoria = categoria;
	}

	/**
	 * Cadena de caracteres con todos los atributos del Tipo.
	 */
	@Override
	public String toString()
	{
		return "Tipo [nombre=" + nombre + ", categoria ="+ categoria + "]";
	}
}