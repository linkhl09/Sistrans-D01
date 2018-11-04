package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto de Orden pedido.
 * @author Andrés Hernández y Jenifer Rodriguez
 */
public class OrdenPedido implements VOOrdenPedido {

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * identificador unico de la orden de pedido.
	 */
	private long id;
	
	/**
	 * fecha en la que se raliza la entrega de la  orden de la entrega.
	 */
	private Date fechaEntrega;
	
	/**
	 * fecha esperada de la entrega de la orden de pedido.
	 */
	private Date fechaEsperadaEntrega;
	
	/**
	 * calificacion que le otorga la sucursal al proveedor por el esatado de los prodcutos entregados.
	 */
	private double calificacionProveedor;

	/**
	 * identificador del proveedor al que se le realiza la orden.
	 */
	private String proveedor;
	
	/**
	 * identificador de la sucursal que realiza la orden.
	 */
	private long idSucursal;
	
	/**
	 * estado en el que se encuentra la orden.
	 */
	private String estado;
	

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public OrdenPedido()
	{
		this.id = 0;
		this.fechaEntrega = null;
		this.fechaEsperadaEntrega = new Date();
		this.calificacionProveedor = 0;
		this.proveedor = "";
		this.idSucursal = 0;
		this.estado ="" ;
	}
	
	/**
	 * 
	 * @param id 
	 * @param fechaEntrega 
	 * @param fechaEsperadaEntrega 
	 * @param calificacionProveedor
	 * @param proveedor- identificador del proveedor al que va dirigida la orden 
	 * @param idSucursal - id de la sucursal que realiza el pedido
	 * @param Estado - estado en el que se encuentra el pedido
	 */
	public OrdenPedido(long id, Date fechaEntrega, Date fechaEsperadaEntrega, double calificacionProveedor,
			String proveedor, long idSucursal, String estado) 
	{
		this.id = id;
		this.fechaEntrega = fechaEntrega;
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
		this.calificacionProveedor = calificacionProveedor;
		this.proveedor = proveedor;
		this.idSucursal = idSucursal;
		this.estado = estado;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------


	/**
	 * @return identificacion de la orden de pedido.
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * Cambia la identificacion de la orden de pedido
	 * @param nueva id  
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return fecha de entrega del pedido.
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * Cambia la fecha de entrega del pedido
	 * @param nueva fecha de entrega
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return fecha esperada de entrega del pedido.
	 */
	public Date getFechaEsperadaEntrega() {
		return fechaEsperadaEntrega;
	}

	/**
	 * Cambia la fecha esperada de entrega del pedido
	 * @param nueva fecha esperada de entrega
	 */
	public void setFechaEsperadaEntrega(Date fechaEsperadaEntrega) {
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
	}

	/**
	 * @return calificacion otorgada al proveedor por la orden.
	 */
	public double getCalificacionProveedor() {
		return calificacionProveedor;
	}

	/**
	 * Cambia la calificacion del proveedor 
	 * @param nueva calificacion 
	 */
	public void setCalificacionProveedor(double calificacionProveedor) {
		this.calificacionProveedor = calificacionProveedor;
	}

	/**
	 * @return identificador del proveedor al que se le hace el pedido.
	 */
	public String getProveedor() {
		return proveedor;
	}

	/**
	 * Cambia el identificador del proveedor 
	 * @param nuevo identificador
	 */
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return identificador de la sucursal que emite la orden de pedido.
	 */
	public long getidSucursal() {
		return idSucursal;
	}

	/**
	 * Cambia el identificador de la sucursal 
	 * @param nuevo identificador
	 */
	public void setidSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	/**
	 * @return estado de la orden.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Cambia el esatdo de la orden
	 * @param nuevo esatado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Cadena de caracteres con todos los atributos de.
	 */
	@Override
	public String toString()
	{
		String fechEn = "";
		if(fechaEntrega!= null)
			fechEn = fechaEntrega.toString();
		return "OrdenPedido [id =" + id + ", fechaEntrega =" + fechEn 
				+ ", fechaEsperadaEntrega =" + fechaEsperadaEntrega.toString() 
				+ ", calificacionProveedor =" + calificacionProveedor 
				+ ", estado=" + estado
				+ ", proveedor =" + proveedor 
				+ ", idSucursal =" + idSucursal 
				 + " ]";
	}
	
}
