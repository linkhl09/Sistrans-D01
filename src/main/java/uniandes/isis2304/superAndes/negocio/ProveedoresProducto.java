package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de .
 * @author Andrés Hernández
 */
public class ProveedoresProducto implements VOProveedoresProducto{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 *numero de identificacion del proveedor.
	 */
	private String proveedor;
	
	/**
	 * codigo del producto .
	 */
	private String producto;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public ProveedoresProducto()
	{
		proveedor = "";
		producto = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param proveedor - numero de identificacion del proveedor
	 * @param producto - codigo del producto
	 */
	public ProveedoresProducto(String proveedor, String producto) 
	{
		this.proveedor = proveedor;
		this.producto = producto;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return identificador del proveedor.
	 */
	public String getProveedor() 
	{
		return proveedor;
	}

	/**
	 * Cambia el proveedor del proveedor
	 * @param nueva identificador 
	 */
	public void setProveedor(String proveedor) 
	{
		this.proveedor = proveedor;
	}

	/**
	 * @return codigo del producto.
	 */
	public String getProducto() 
	{
		return producto;
	}

	/**
	 * Cambia el producto
	 * @param nuevo codigo
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de ProveedoresProducto.
	 */
	@Override
	public String toString()
	{
		return "ProveedoresProducto [proveedor =" + proveedor + ", producto =" + producto +"]";
	}
}
