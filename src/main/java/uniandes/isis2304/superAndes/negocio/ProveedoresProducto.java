package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de .
 * @author Andrés Hernández
 */
public class ProveedoresProducto implements VOProveedoresProducto{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private String proveedor;
	
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
	 * @param proveedor
	 * @param producto
	 */
	public ProveedoresProducto(String proveedor, String producto) 
	{
		this.proveedor = proveedor;
		this.producto = producto;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	
	public String getProveedor() 
	{
		return proveedor;
	}

	public void setProveedor(String proveedor) 
	{
		this.proveedor = proveedor;
	}

	public String getProducto() 
	{
		return producto;
	}

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
