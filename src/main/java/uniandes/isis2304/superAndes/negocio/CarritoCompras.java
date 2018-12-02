package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de CarritoCompras
 * @author Andrés Hernández y Jenifer Rodriguez.
 */
public class CarritoCompras implements VOCarritoCompras {
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * Identificador único del carrito de compras.
	 */
	private long id;
	
	/**
	 * Correo eletrónico del cliente dueño del carrito de compras.
	 */
	private String cliente;
	
	/**
	 * Id de la sucursal donde esta carrito de compras.
	 */
	private long idSucursal;

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
		setIdSucursal(0);
	}
	
	/**
	 * Constructor con valores. 
	 * @param id - El identificador del nuevo carrito de compras.
	 * @param cliente -Correo eletrónico del cliente dueño del carrito de compras.
	 */
	public CarritoCompras(long id, String cliente,long idSucursal)
	{
		this.id = id;
		this.cliente = cliente;
		this.idSucursal = idSucursal;
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
	 * @return El identificador del cliente dueño del carrito de compras.
	 */
	public String getCliente()
	{
		return cliente;
	}
	
	/**
	 * Asigna el cliente al carrito de compras.
	 * @param cliente - El nuevo dueño del carrito de compras.
	 */
	public void setCliente(String cliente)
	{
		this.cliente = cliente;
	}
	
	
	/**
	 * @return El identificador de la sucursal donde esta el carrito de compras.
	 */
	public long getIdSucursal() 
	{
		return idSucursal;
	}

	/**
	 * Asigna una nueva sucursal al carrito de compras.
	 * @param idSucursal - El nuevo id de la sucursal donde se encuentra el carrito de compras.
	 */
	public void setIdSucursal(long idSucursal) 
	{
		this.idSucursal = idSucursal;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de la CarritoCompras.
	 */
	@Override
	public String toString()
	{
		return "CarritoCompras [id=" + id + ", cliente= "+ cliente + ", sucursal=" + idSucursal +"]";
	}
}