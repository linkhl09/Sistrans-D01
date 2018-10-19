package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Interfaz para los métodos get de Orden Pedido.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOOrdenPedido {

	public long getId();
	
	
	public Date getFechaEntrega();
	
	
	public Date getFechaEsperadaEntrega();
	
	
	public double getCalificacionProveedor();
	
	
	public String getProveedor();
	
	
	public String getDireccionSucursal();
	
	
	public String getCiudadSucursal();
	
	/**
	 * Cadena de caracteres con todos los atributos de.OrdenPedido
	 */
	@Override
	public String toString();
}
