package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de SucursalProducto.
 * @author Andrés Hernández
 */
public class SucursalProducto implements VOSucursalProducto{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 * Id de la sucursal a la que pertenece el producto.
	 */
	private long idSucursal;
	
	/**
	 * Producto asociado a la sucursal.
	 */
	private String producto;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public SucursalProducto() 
	{
		idSucursal = 0;
		producto = "";
	}

	/**
	 * Constructor con valores.
	 * @param idSucursal id de la sucursal a la que pertenece el producto.
	 * @param producto producto asociado a la sucursal.
	 */
	public SucursalProducto(long idSucursal, String producto) 
	{
		this.idSucursal = idSucursal;
		this.producto = producto;
	}
   
	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return La id de la sucursal dueña del producto.
	 */
	public long getIdSucursal() 
	{
		return idSucursal;
	}

	/**
	 * @param idSucursal - Id de la sucursal a la que pertenece el producto.
	 */
	public void setIdSucursal(long idSucursal) 
	{
		this.idSucursal = idSucursal;
	}

	/**
	 * @return El codigo de barras del producto asociado a la sucursal.
	 */
	public String getProducto() 
	{
		return producto;
	}

	/**
	 * @param producto - Nuevo producto asociado a la sucursal.
	 */
	public void setProducto(String producto) 
	{	
		this.producto = producto;
	}

	/**
	 * Cadena de caracteres con todos los atributos de SucursalProducto.
	 */
	@Override
	public String toString()
	{
		return "SucursalProducto [idSucursal =" + idSucursal 
				+ ", producto" + producto +"]";
	}
}
