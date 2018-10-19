package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto de Orden pedido.
 * @author Andrés Hernández
 */
public class OrdenPedido implements VOOrdenPedido {

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	
	private long id;
	
	
	private Date fechaEntrega;
	
	
	private Date fechaEsperadaEntrega;
	
	
	private double calificacionProveedor;

	
	private String proveedor;
	
	
	private String direccionSucursal;
	
	
	private String ciudadSucursal;

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
		this.direccionSucursal = "";
		this.ciudadSucursal = "";
	}
	
	/**
	 * 
	 * @param id
	 * @param fechaEntrega
	 * @param fechaEsperadaEntrega
	 * @param calificacionProveedor
	 * @param proveedor
	 * @param direccionSucursal
	 * @param ciudadSucursal
	 */
	public OrdenPedido(long id, Date fechaEntrega, Date fechaEsperadaEntrega, double calificacionProveedor,
			String proveedor, String direccionSucursal, String ciudadSucursal) 
	{
		this.id = id;
		this.fechaEntrega = fechaEntrega;
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
		this.calificacionProveedor = calificacionProveedor;
		this.proveedor = proveedor;
		this.direccionSucursal = direccionSucursal;
		this.ciudadSucursal = ciudadSucursal;
	}

    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	public long getId() 
	{
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaEsperadaEntrega() {
		return fechaEsperadaEntrega;
	}

	public void setFechaEsperadaEntrega(Date fechaEsperadaEntrega) {
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
	}

	public double getCalificacionProveedor() {
		return calificacionProveedor;
	}

	public void setCalificacionProveedor(double calificacionProveedor) {
		this.calificacionProveedor = calificacionProveedor;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	public String getCiudadSucursal() {
		return ciudadSucursal;
	}

	public void setCiudadSucursal(String ciudadSucursal) {
		this.ciudadSucursal = ciudadSucursal;
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
				+ ", proveedor =" + proveedor 
				+ ", direccionSucursal =" + direccionSucursal 
				+ ", ciudadSucursal =" + ciudadSucursal + "]";
	}
}
