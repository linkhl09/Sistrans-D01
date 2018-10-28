package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto PRODUCTOCARRITO.
 *
 * @author Andrés Hernández
 */
public class ProductoCarrito implements VOProductoCarrito
{
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * Identificador del carrito de compras.
	 */
	private long carrito;
	
	/**
	 * Cantidad del producto que esta en el carrito.
	 */
	private int cantidad;
	
	/**
	 * Identificador del producto puesto en el carrito.
	 */
	private String codigoBarrasProducto;
	
	// -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------
	
	/**
	 * Cpnstructor vacio.
	 */
	public ProductoCarrito()
	{
		carrito = 0;
		cantidad = 0;
		codigoBarrasProducto = "";
	}

	/**
	 * Constructor con valores. 
	 * @param carrito - Identificador del carrito de compras.
	 * @param cantidad - Cantidad del producto en el carrito.
	 * @param codigoBarras - Identificador del producto puesto en el carrito.
	 */
	public ProductoCarrito(long carrito, int cantidad, String codigoBarras) 
	{
		this.carrito = carrito;
		this.cantidad = cantidad;
		this.codigoBarrasProducto = codigoBarras;
	}

	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	/**
	 * @return El identificador del carrito de compras.
	 */
	public long getCarrito() 
	{
		return carrito;
	}

	/**
	 * Asigna el identificador del carrito de compras.
	 * @param carrito - el nuevo carrito de compras.
	 */
	public void setCarrito(long carrito) 
	{
		this.carrito = carrito;
	}

	/**
	 * @return La cantidad de unidades del producto.
	 */
	public int getCantidad() 
	{
		return cantidad;
	}

	/**
	 * Asigna la cantidad de unidades del producto puestas en el carrito de compras.
	 * @param cantidad - La nueva cantidad del producto.
	 */
	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	/**
	 * @return El identificador del Producto puesto en el carrito de compras.
	 */
	public String getCodigoBarrasProducto() 
	{
		return codigoBarrasProducto;
	}

	/**
	 * Asigna el producto que se ha puesto en el carrito de compras.
	 * @param codigoBarrasProducto - El identificador del producto puesto en el carrito.
	 */
	public void setCodigoBarrasProducto(String codigoBarrasProducto) 
	{
		this.codigoBarrasProducto = codigoBarrasProducto;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de el ProductoCarrito.
	 */
	@Override
	public String toString()
	{
		return "ProductoCarrito [carrito = " + carrito + ", cantidad = " + cantidad 
				+ ", codigoBarrasProducto =" + codigoBarrasProducto + "]";
	}
}