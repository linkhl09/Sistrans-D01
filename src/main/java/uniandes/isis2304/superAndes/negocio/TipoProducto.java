package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de TipoCategoria.
 * @author Andrés Hernández
 */
public class TipoProducto implements VOTipoProducto
{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * Indentificador del producto.
	 */
	private String codigoBarrasProducto;

	/**
	 * Tipo del producto.
	 */
	private String nombreTipo;
	
	// -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public TipoProducto()
	{
		codigoBarrasProducto = "";
		nombreTipo =""; 
	}
	
	
	/**
	 * Constructor con valores. 
	 * @param codigoBarrasProducto - Identificador del producto.
	 * @param nombreTipo Tipo del producto.
	 */
	public TipoProducto(String codigoBarrasProducto, String nombreTipo) 
	{
		this.codigoBarrasProducto = codigoBarrasProducto;
		this.nombreTipo = nombreTipo;
	}
	
    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------	
	
	/**
	 * @return Nombre del tipo del producto.
	 */
	public String getNombreTipo() 
	{
		return nombreTipo;
	}

	/**
	 * Set del tipo del producto.
	 * @param nombreTipo Nuevo tipo.
	 */
	public void setNombreTipo(String nombreTipo) 
	{
		this.nombreTipo = nombreTipo;
	}

	/**
	 * @return Identificador del producto.
	 */
	public String getCodigoBarrasProducto() 
	{
		return codigoBarrasProducto;
	}

	/**
	 * Set del producto 
	 * @param codigoBarrasProducto - nuevo producto asociado.
	 */
	public void setNombreCategoria(String codigoBarrasProducto) 
	{
		this.codigoBarrasProducto = codigoBarrasProducto;
	}
	
	@Override
	public String toString()
	{
		return "TipoCategoria [codigoBarrasProducto=" +codigoBarrasProducto +", nombreTipo=" + nombreTipo +"]";
	}
}