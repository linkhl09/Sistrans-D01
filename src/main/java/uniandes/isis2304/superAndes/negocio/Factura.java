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
	 * direccion de la factura.
	 */
	private String direccion;
	
	/**
	 * Fecha de la factura.
	 */
	private Date fecha;
	
	/**
	 * nombre del cajero de la factura.
	 */
	private String nombreCajero;
	
	/**
	 * valor total de la factura.
	 */
	private double valorTotal;
	
	/**
	 * pago exitoso de la compra.
	 */
	private int pagoExitoso;
	
	/**
	 * puntos de la factura.
	 */
	private int puntosCompra;
	
	/**
	 * CLiente que realiza la compra.
	 */
	private String cliente;

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
		this.pagoExitoso = 0;
		this.puntosCompra = 0;
		this.cliente = "";
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
	 * @param cliente CLiente que realiza la compra.
	 */
	public Factura(long numero, String direccion, Date fecha, String nombreCajero, double valorTotal,
			int pagoExitoso, int puntosCompra, String cliente) 
	{
		this.numero = numero;
		this.direccion = direccion;
		this.fecha = fecha;
		this.nombreCajero = nombreCajero;
		this.valorTotal = valorTotal;
		this.pagoExitoso = pagoExitoso;
		this.puntosCompra = puntosCompra;
		this.cliente = cliente;
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
	public int isPagoExitoso() {
		return pagoExitoso;
	}

	/**
	 * Asigna si el pago fue exitoso.
	 * @param pagoExitoso pago Exitoso. 1 éxito. 0 Failure.
	 */
	public void setPagoExitoso(int pagoExitoso) 
	{
		this.pagoExitoso = pagoExitoso;
	}

	/**
	 * @return Los puntos de la cabesra.
	 */
	public int getPuntosCompra() 
	{
		return puntosCompra;
	}

	/**
	 * Asigna los puntos de la compra.
	 * @param puntosCompra asigna los puntos de la compra.
	 */
	public void setPuntosCompra(int puntosCompra) 
	{
		this.puntosCompra = puntosCompra;
	}

	/**
	 * @return cliente de la factura.
	 */
	public String getCliente() 
	{
		return cliente;
	}

	/**
	 * Asigna el cliente de la factura.
	 * @param cliente nuevo cliente de la factura.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * Cadena de caracteres con todos los atributos de la factura.
	 */
	@Override
	public String toString()
	{
		return "Factura [numero =" + numero + ", direccion =" + direccion + ", fecha =" + fecha.toString() 
				+ ", valorTotal =" + valorTotal + ", pagoExitoso =" + pagoExitoso + ", puntosCompra =" + puntosCompra 
				+ ", cliente =" + cliente + "]";
	}	
}
