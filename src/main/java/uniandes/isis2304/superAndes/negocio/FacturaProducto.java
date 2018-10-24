package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de .
 * @author Andrés Hernández y jenifer Rodriguez
 */
public class FacturaProducto implements VOFacturaProducto{

	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------
	/**
	 * numero de la factura a  la que esta asociado el producto
	 */
	private long factura;

	/**
	 * cantidad del producto
	 */	
	private int cantidad;

	/**
	 * codigo del producto asociado a la factura 
	 */	
	private String producto;

	// -----------------------------------------------------------------
	// Constructores.
	// -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public FacturaProducto()
	{
		factura = 0;
		cantidad = 0;
		producto = "";
	}

	/**
	 * Constructor con valores.
	 * @param numero de la factura a  la que esta asociado el producto
	 * @param cantidad del producto
	 * @param codigo del producto asociado a la factura 
	 */
	public FacturaProducto(long factura, int cantidad, String producto) 
	{
		this.factura = factura;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	// -----------------------------------------------------------------
	// Métodos.
	// -----------------------------------------------------------------
	
	/**
	 * @return numero de la factura .
	 */
	public long getFactura() 
	{
		return factura;
	}

	/**
	 * Cambia el numero de factura
	 * @param nuevo numero de la factura  
	 */
	public void setFactura(long factura) 
	{
		this.factura = factura;
	}

	/**
	 * @return cantidad del producto.
	 */
	public int getCantidad()
	{
		return cantidad;
	}

	/**
	 * Cambia la cantidad del producto
	 * @param nueva cantidad  
	 */
	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	/**
	 * @return codigo del producto.
	 */
	public String getProducto() 
	{
		return producto;
	}

	/**
	 * Cambia elproducto
	 * @param nuevo codigo de producto 
	 */
	public void setProducto(String producto) 
	{
		this.producto = producto;
	}

	/**
	 * Cadena de caracteres con todos los atributos de FacturaProducto.
	 */
	@Override
	public String toString()
	{
		return "FacturaProducto [numero de factura =" + factura + ", cantidad del producto =" + cantidad
				+", producto ="+ producto +"]";
	}
}
