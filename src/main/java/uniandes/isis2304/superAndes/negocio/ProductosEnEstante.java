package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de ProductosEnEstante.
 * @author Andrés Hernández
 */
public class ProductosEnEstante implements VOProductosEnEstante {

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 * Id del estante donde se almacena el producto.
	 */
	private long idEstante;
	
	/**
	 * Cantidad de unidades del producto almacenado en el estante.
	 */
	private int cantidad;
	
	/**
	 * Codigo de barras del producto almacenado en el estante.
	 */
	private String codigoBarrasProducto;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public ProductosEnEstante() 
	{
		idEstante = 0;
		cantidad = 0;
		codigoBarrasProducto = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param idEstante Id del estante donde se almacena el producto.
	 * @param cantidad Cantidad de productos que se almacenan en el estante.
	 * @param codigoBarrasProducto Codigo de barras del producto almacenado en el estante.
	 */
	public ProductosEnEstante(long idEstante, int cantidad, String codigoBarrasProducto) 
	{
		this.idEstante = idEstante;
		this.cantidad = cantidad;
		this.codigoBarrasProducto = codigoBarrasProducto;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return id del estante donde se guarda el producto.
	 */
	public long getIdEstante() 
	{
		return idEstante;
	}

	/**
	 * @param idEstante - Nuevo id de estante donde se almacena el producto.
	 */
	public void setIdEstante(long idEstante) 
	{
		this.idEstante = idEstante;
	}

	/**
	 * @return cantidad del producto guardado en el estante.
	 */
	public int getCantidad() 
	{
		return cantidad;
	}

	/**
	 * @param cantidad - Nueva cantidad de productos en el estante.
	 */
	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	/**
	 * @return Codigo de barras del producto guardado en el estante.
	 */
	public String getCodigoBarrasProducto() 
	{
		return codigoBarrasProducto;
	}

	/**
	 * @param codigoBarrasProducto - Codigo de barras del producto almacenado en el estante.
	 */
	public void setCodigoBarrasProducto(String codigoBarrasProducto) 
	{
		this.codigoBarrasProducto = codigoBarrasProducto;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de ProductosEnEstante.
	 */
	@Override
	public String toString()
	{
		return "ProductosEnEstante [ estante ="+ idEstante +", cantidad =" +cantidad
				+ "producto =" + codigoBarrasProducto + "]";
	}
}
