package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

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
	 * Clodigo del producto asociado a la orden de pedido.
	 */
	private String producto;
	
	/**
	 * fecha en la que se agrega el producto a la orden
	 */
	private Date fechaAgregado;
	

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
		fechaAgregado =null;
	}
	
	/**
	 * Constructor con valores.
	 * @param pedido - numero identificador de la orden de pedido
	 * @param cantidad - cantidad del producto
	 * @param calidad - calidad de los productos entregados
	 * @param producto - codigo dle producto asociado a la orden de pedido
	 * @param fechaAagregado - fecha en la que se agrega el producto a la orden
	 */
	public ProductoOrdenPedido(long pedido, int cantidad, double calidad, String producto,Date fechaAgregado) {
		this.pedido = pedido;
		this.cantidad = cantidad;
		this.calidad = calidad;
		this.producto = producto;
		this.fechaAgregado = fechaAgregado;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return  numero identificador de la orden de pedido.
	 */
	public long getPedido() 
	{
		return pedido;
	}

	/**
	 * Cambia el numero identificador de la orden de pedido
	 * @param nueva identificador de pedido
	 */
	public void setPedido(long pedido) 
	{
		this.pedido = pedido;
	}

	/**
	 * @return Cantidad del producto que se solicita en la orden de pedido.
	 */
	public int getCantidad() 
	{
		return cantidad;
	}

	/**
	 * Cambia la Cantidad del producto que se solicita en la orden de pedido
	 * @param nueva cantidad de producto
	 */
	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	/**
	 * @return calificacion de calidad de los productos entregados en la orden.
	 */
	public double getCalidad() 
	{
		return calidad;
	}

	public void setCalidad(double calidad) 
	{
		this.calidad = calidad;
	}

	/**
	 * @return Codigo del producto asociado a la orden de pedido.
	 */
	public String getProducto() 
	{
		return producto;
	}

	/**
	 * Cambia el  Codigo del producto asociado a la orden de pedido
	 * @param nueva codigo de producto
	 */
	public void setProducto(String producto) 
	{
		this.producto = producto;
	}

	/**
	 * @return fecha en la que se agrega el producto a la orden.
	 */
	public Date getFechaAgregado() {
		return fechaAgregado;
	}

	/**
	 * Cambia la fecha en la que se agrega el producto a la orden
	 * @param nueva fecha de agregado
	 */
	public void setFechaAgregado(Date fechaAgregado) {
		this.fechaAgregado = fechaAgregado;
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
