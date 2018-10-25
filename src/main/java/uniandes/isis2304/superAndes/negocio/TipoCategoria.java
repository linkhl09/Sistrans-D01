package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de TipoCategoria.
 * @author Andrés Hernández
 */
public class TipoCategoria implements VOTipoCategoria
{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * Nombre de la categoria.
	 */
	private String nombreCategoria;

	/**
	 * Tipo de la categoria.
	 */
	private String nombreTipo;
	
	// -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public TipoCategoria()
	{
		nombreCategoria = "";
		nombreTipo =""; 
	}
	
	
	/**
	 * Constructor con valores. 
	 * @param nombreCategoria Nombre de la categoria.
	 * @param nombreTipo Tipo de la categoria.
	 */
	public TipoCategoria(String nombreCategoria, String nombreTipo) 
	{
		this.nombreCategoria = nombreCategoria;
		this.nombreTipo = nombreTipo;
	}
	
    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------	
	
	/**
	 * @return Nombre del tipo de la categoria.
	 */
	public String getNombreTipo() 
	{
		return nombreTipo;
	}

	/**
	 * Set del tipo de la categoria.
	 * @param nombreTipo Nuevo tipo.
	 */
	public void setNombreTipo(String nombreTipo) 
	{
		this.nombreTipo = nombreTipo;
	}

	/**
	 * @return Nombre de la categoria.
	 */
	public String getNombreCategoria() 
	{
		return nombreCategoria;
	}

	/**
	 * Set de la categoria.
	 * @param categoria nueva categoria.
	 */
	public void setNombreCategoria(String categoria) 
	{
		this.nombreCategoria = categoria;
	}
	
	@Override
	public String toString()
	{
		return "TipoCategoria [nombreCategoria=" +nombreCategoria +", nombreTipo=" + nombreTipo +"]";
	}
}