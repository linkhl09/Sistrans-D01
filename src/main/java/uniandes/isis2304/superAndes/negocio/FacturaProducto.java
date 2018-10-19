package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de .
 * @author Andrés Hernández
 */
public class FacturaProducto implements VOFacturaProducto{
	
	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private long factura;
	
	private int cantidad;
	
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
	 * @param factura
	 * @param cantidad
	 * @param producto
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

	public long getFactura() 
	{
		return factura;
	}

	public void setFactura(long factura) 
	{
		this.factura = factura;
	}

	public int getCantidad()
	{
		return cantidad;
	}

	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	public String getProducto() 
	{
		return producto;
	}

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
		return "FacturaProducto [factura =" + factura + ", cantidad =" + cantidad
				+", producto ="+ producto +"]";
	}
}
