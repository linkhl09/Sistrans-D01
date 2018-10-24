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
	
	
	// -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------
	
	/**
	 * Constructor vacio.
	 */
	public Tipo()
	{
		nombre="";
	}
	
	/**
	 * Constructor con valores.
	 * @param nombre
	 */
	public Tipo(String nombre)
	{
		this.nombre = nombre;
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
	 * Cadena de caracteres con todos los atributos del Tipo.
	 */
	@Override
	public String toString()
	{
		return "Tipo [nombre=" + nombre + "]";
	}
}