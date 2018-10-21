package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de ProductoEnBodega.
 * @author Andrés Hernández
 */
public class ProductosEnBodega implements VOProductosEnBodega {

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	/**
	 * id de la bodega a la que pertenece el producto.
	 */
	private long idBodega;
	
	/**
	 * Cantidad de unidades del producto que tiene esa bodega.
	 */
	private int cantidad;
	
	/**
	 * Nivel de abastecimiento del producto en esa bodega.
	 */
	private  int nivelAbastecimiento;
	
	/**
	 * Producto almacenado en la bodega.
	 */
	private String codigoBarrasproducto;	

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public ProductosEnBodega() 
	{
		idBodega = 0;
		cantidad = 0; 
		nivelAbastecimiento = 0;
		codigoBarrasproducto = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param idBodega id de la bodega donde se almacenará el producto.
	 * @param cantidad Cantidad de unidades del producto almacenado en bodega.
	 * @param nivelAbastecimiento nivel de abastecimiento de ese producto en la bodega.
	 * @param codigoBarrasProducto
	 */
	public ProductosEnBodega(long idBodega, int cantidad, int nivelAbastecimiento, String codigoBarrasProducto) {
		this.idBodega = idBodega;
		this.cantidad = cantidad;
		this.nivelAbastecimiento = nivelAbastecimiento;
		this.codigoBarrasproducto = codigoBarrasProducto;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return  id de la bodega donde esta almacenado el producto.
	 */
	public long getIdBodega() 
	{
		return idBodega;
	}

	/**
	 * @param idBodega Id de la nueva bodega donde se almacenará el producto.
	 */
	public void setIdBodega(long idBodega) 
	{
		this.idBodega = idBodega;
	}

	/**
	 * @return Cantidad de unidades del producto almacenado en bodega.
	 */
	public int getCantidad() 
	{
		return cantidad;
	}

	/**
	 * @param cantidad nueva cantidad del producto almacenado en bodega.
	 */
	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	/**
	 * @return Nivel de abastecimiento del producto almacenado en bodega.
	 */
	public int getNivelAbastecimiento() 
	{
		return nivelAbastecimiento;
	}

	/**
	 * @param nivelAbastecimiento - Nuevo nivel de abastecimiento de el producto almacenado en bodega.
	 */
	public void setNivelAbastecimiento(int nivelAbastecimiento) 
	{
		this.nivelAbastecimiento = nivelAbastecimiento;
	}

	/**
	 * @return codigo de barras del producto almacenado en bodega.
	 */
	public String getCodigoBarrasProducto() 
	{
		return codigoBarrasproducto;
	}

	/**
	 * @param codigoBarrasProducto El producto que se va a almacenar en la bodega.
	 */
	public void setCodigoBarrasProducto(String codigoBarrasProducto) 
	{
		this.codigoBarrasproducto = codigoBarrasProducto;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de ProductosEnBodega.
	 */
	@Override
	public String toString()
	{
		return "ProductosEnBodega [idBodega =" + idBodega + ", cantidad =" + cantidad 
				+ ", nivelAbastecimiento =" + nivelAbastecimiento 
				+ ", codigoBarrasProducto =" + codigoBarrasproducto + "]";
	}	
}
