package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto de ProductoOrdenPedido.
 * @author Andrés Hernández y jenifer Rodriguez
 */
public class ProductoOrdenPedido implements VOProductoOrdenPedido{

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * numero identificador de la orden de pedido.
	 */
	private long pedido;
	
	/**
	 * Cantidad del producto que se solicita en la orden de pedido.
	 */
	private int cantidad;
	
	/**
	 * calificacion de calidad de los productos entregados en la orden.
	 */
	private double calidad;
	
	/**
	 * Clodigo del producto asiciado a la orden de pedido.
	 */
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
	 * @param pedido - numero identificador de la orden de pedido
	 * @param cantidad - cantidad del producto
	 * @param calidad - calidad de los productos entregados
	 * @param producto - codigo dle producto asociado a la orden de pedido
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
