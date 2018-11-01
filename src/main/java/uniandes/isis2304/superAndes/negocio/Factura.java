package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto de Factura.
 * @author Andrés Hernández
 */
public class Factura implements VOFactura {

	// -----------------------------------------------------------------
    // Atributos.
    // -----------------------------------------------------------------
	
	/**
	 * numero de la factura.
	 */
	private long numero;

	/**
	 * direccion de la sucursal donde se genera la factura.
	 */
	private String direccion;
	
	/**
	 * Fecha de generacion de la factura.
	 */
	private Date fecha;
	
	/**
	 * Nombre del cajero q expide la factura.
	 */
	private String nombreCajero;
	
	/**
	 * valor total de la factura.
	 */
	private double valorTotal;
	
	/**
	 * dice si el pago  de la compra fue exitoso o no.
	 */
	private boolean pagoExitoso;
	
	/**
	 * puntos  de la factura.
	 */
	private int puntosCompra;
	
	/**
	 * correo electronico del CLiente que realiza la compra
	 */
	private String correoCliente;
	
	/**
	 * Id de la sucursal en donde se realiza la compra
	 */
	private Long idSucursal;
	
	

    // -----------------------------------------------------------------
    // Constructores.
    // -----------------------------------------------------------------

	/**
	 * Constructor vacio.
	 */
	public Factura()
	{
		this.numero = 0;
		this.direccion = "";
		this.fecha = new Date();
		this.nombreCajero = "";
		this.valorTotal = 0;
		this.pagoExitoso = false;
		this.puntosCompra = 0;
		this.correoCliente = "";
		this.setIdSucursal((long) 0);
	}

	/**
	 * Constructor con valores
	 * @param numero numero de la factura.
	 * @param direccion direccion de la factura
	 * @param fecha Fecha de la factura.
	 * @param nombreCajero nombre del cajero de la factura.
	 * @param valorTotal valor total de la factura.
	 * @param pagoExitoso pago exitoso de la compra.
	 * @param puntosCompra puntos de la factura.
	 * @param correoCliente CLiente que realiza la compra.
	 * @param idSucursal identificador de la sucursal donde se realiza la compra
	 */
	public Factura(long numero, String direccion, Date fecha, String nombreCajero, double valorTotal,
			boolean pagoExitoso, int puntosCompra, String correoCliente, long idSucursal) 
	{
		this.numero = numero;
		this.direccion = direccion;
		this.fecha = fecha;
		this.nombreCajero = nombreCajero;
		this.valorTotal = valorTotal;
		this.pagoExitoso = pagoExitoso;
		this.puntosCompra = puntosCompra;
		this.correoCliente = correoCliente;
		this.setIdSucursal(idSucursal);
	}
    // -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------

	/**
	 * @return numero de la factura.
	 */
	public long getNumero() 
	{
		return numero;
	}

	/**
	 * Asigna el número de la factura.
	 * @param numero asigna número de la factura.
	 */
	public void setNumero(long numero) 
	{
		this.numero = numero;
	}

	/**
	 * @return direccion de la compra.
	 */
	public String getDireccion() 
	{
		return direccion;
	}
	
	/**
	 * Asigna la direccion de la compra.
	 * @param direccion nueva direccion de la compra.
	 */
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}

	/**
	 * @return fecha de compra.
	 */
	public Date getFecha() 
	{
		return fecha;
	}

	/**
	 * Asigna la fecha de compra.
	 * @param fecha nueva fecha de compra.
	 */
	public void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}

	/**
	 * @return nombre del cajero.
	 */
	public String getNombreCajero() 
	{
		return nombreCajero;
	}

	/**
	 * Asigna el nombre del cajero.
	 * @param nombreCajero nuevo nombre del cajero.
	 */
	public void setNombreCajero(String nombreCajero) 
	{
		this.nombreCajero = nombreCajero;
	}

	/**
	 * @return valor total de la factua.
	 */
	public double getValorTotal() 
	{
		return valorTotal;
	}

	/**
	 * Asigna el valor total.
	 * @param valorTotal nuevo valor total.
	 */
	public void setValorTotal(double valorTotal) 
	{
		this.valorTotal = valorTotal;
	}

	/**
	 * @return pago exitoso. 1 si exito, 0 de lo contrario.
	 */
	public boolean isPagoExitoso() {
		return pagoExitoso;
	}

	/**
	 * Asigna si el pago fue exitoso o no.
	 * @param pagoExitoso pago Exitoso. 1 éxito. 0 Failure.
	 */
	public void setPagoExitoso(boolean pagoExitoso) 
	{
		this.pagoExitoso = pagoExitoso;
	}

	/**
	 * @return Los puntos que se asignan por la compra.
	 */
	public int getPuntosCompra() 
	{
		return puntosCompra;
	}

	/**
	 * Asigna los puntos que se dan por la compra.
	 * @param puntosCompra asigna los puntos de la compra.
	 */
	public void setPuntosCompra(int puntosCompra) 
	{
		this.puntosCompra = puntosCompra;
	}

	/**
	 * @return cliente de la factura.
	 */
	public String getCorreoCliente() 
	{
		return correoCliente;
	}

	/**
	 * Asigna el cliente de la factura.
	 * @param cliente nuevo cliente de la factura.
	 */
	public void setCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	/**
	 * Cadena de caracteres con todos los atributos de la factura.
	 */
	@Override
	public String toString()
	{
		return "Factura [numero =" + numero + ", direccion =" + direccion + ", fecha =" + fecha.toString() 
				+ ", valorTotal =" + valorTotal + ", pagoExitoso =" + pagoExitoso + ", puntosCompra =" + puntosCompra 
				+ ", correo del cliente =" + correoCliente + "]";
	}

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}	
}
