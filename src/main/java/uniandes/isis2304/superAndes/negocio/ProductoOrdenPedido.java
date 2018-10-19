package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de .
 * @author Andrés Hernández
 */
public class ProductoOrdenPedido implements VOProductoOrdenPedido{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------

	private long pedido;
	
	private int cantidad;
	
	private double calidad;
	
	private String producto;

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public ProductoOrdenPedido()
	{
		pedido = 0;
		cantidad = 0;
		calidad = 0;
		producto = "";
	}
	
	/**
	 * Constructor con valores.
	 * @param pedido
	 * @param cantidad
	 * @param calidad
	 * @param producto
	 */
	public ProductoOrdenPedido(long pedido, int cantidad, double calidad, String producto) {
		this.pedido = pedido;
		this.cantidad = cantidad;
		this.calidad = calidad;
		this.producto = producto;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	public long getPedido() 
	{
		return pedido;
	}

	public void setPedido(long pedido) 
	{
		this.pedido = pedido;
	}

	public int getCantidad() 
	{
		return cantidad;
	}

	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	public double getCalidad() 
	{
		return calidad;
	}

	public void setCalidad(double calidad) 
	{
		this.calidad = calidad;
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
	 * Cadena de caracteres con todos los atributos de ProductoOrdenPedido.
	 */
	@Override
	public String toString()
	{
		return "ProductoOrdenPedido [pedido =" + pedido + ", cantidad =" + cantidad 
				+ ", calidad =" + calidad +",producto = " + producto +"]";
	}
}
