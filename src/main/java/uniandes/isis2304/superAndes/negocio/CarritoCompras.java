package uniandes.isis2304.superAndes.negocio;

public class CarritoCompras implements VOCarritoCompras
{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * Identificador único del carrito de compras.
	 */
	private long id;

	// -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public CarritoCompras()
	{
		id= 0;
	}
	
	/**
	 * Constructor con valores. 
	 * @param id - El identificador del nuevo carrito de compras.
	 */
	public CarritoCompras(long id)
	{
		this.id = id;
	}
	
	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	/**
	 * @return El identificador del carrito de compras.
	 */
	public long getId()
	{
		return id;
	}
	
	/**
	 * Asigna el id al Carrito de compras.
	 * @param id - nuevo id.
	 */
	public void setId(long id)
	{
		this.id = id;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de la CarritoCompras.
	 */
	@Override
	public String toString()
	{
		return "CarritoCompras [id=" + id +"]";
	}	
}