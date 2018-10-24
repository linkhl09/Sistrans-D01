package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Interfaz para los m�todos get de Factura.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * @author Andr�s Hern�ndez.
 */
public interface VOFactura 
{
	
	/**
	 * @return numero de la factura.
	 */
	public long getNumero();
	/**
	 * @return direccion de la compra.
	 */
	public String getDireccion();
	
	/**
	 * @return fecha de compra.
	 */
	public Date getFecha();	
	
	/**
	 * @return nombre del cajero.
	 */
	public String getNombreCajero();
	
	/**
	 * @return pago exitoso. 1 si exito, 0 de lo contrario.
	 */
	public boolean isPagoExitoso();
	
	/**
	 * @return asigna los puntos de la cabesra.
	 */
	public int getPuntosCompra();
		
	/**
	 * @return cliente de la factura.
	 */
	public String getCorreoCliente();
	
	/**
	 * Cadena de caracteres con todos los atributos de la factura.
	 */
	@Override
	public String toString();
}
