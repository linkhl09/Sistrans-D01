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
	private String codigoBarrasProducto;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public SucursalProducto() 
	{
		idSucursal = 0;
		codigoBarrasProducto = "";
	}

	/**
	 * Constructor con valores.
	 * @param idSucursal id de la sucursal a la que pertenece el producto.
	 * @param codigoBarras producto asociado a la sucursal.
	 */
	public SucursalProducto(long idSucursal, String codigoBarras) 
	{
		this.idSucursal = idSucursal;
		this.codigoBarrasProducto = codigoBarras;
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
	public String getCodigoBarrasProducto() 
	{
		return codigoBarrasProducto;
	}

	/**
	 * @param codigoBarrasProducto - Nuevo producto asociado a la sucursal.
	 */
	public void setCodigoBarrasProducto(String codigoBarrasProducto) 
	{	
		this.codigoBarrasProducto = codigoBarrasProducto;
	}

	/**
	 * Cadena de caracteres con todos los atributos de SucursalProducto.
	 */
	@Override
	public String toString()
	{
		return "SucursalProducto [idSucursal =" + idSucursal 
				+ ", codigoBarrasProducto" + codigoBarrasProducto +"]";
	}
}
