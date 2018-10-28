package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de CarritoCompras
 * @author Andr�s Hern�ndez y Jenifer Rodriguez.
 */
public class CarritoCompras implements VOCarritoCompras {
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * Identificador �nico del carrito de compras.
	 */
	private long id;
	
	/**
	 * Correo eletr�nico del cliente due�o del carrito de compras.
	 */
	private String cliente;

	// -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public CarritoCompras()
	{
		id= 0;
		cliente = "";
	}
	
	/**
	 * Constructor con valores. 
	 * @param id - El identificador del nuevo carrito de compras.
	 */
	public CarritoCompras(long id, String cliente)
	{
		this.id = id;
		this.cliente = cliente;
	}
	
	// -----------------------------------------------------------------
    // M�todos.
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
	 * @return El identificador del cliente due�o del carrito de compras.
	 */
	public String getCliente()
	{
		return cliente;
	}
	
	/**
	 * Asigna el cliente al carrito de compras.
	 * @param cliente - El nuevo due�o del carrito de compras.
	 */
	public void setCliente(String cliente)
	{
		this.cliente = cliente;
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