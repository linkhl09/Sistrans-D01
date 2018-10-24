package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

/**
 * Interfaz para los métodos get de Orden Pedido.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández.
 */
public interface VOOrdenPedido {

	/**
	 * @return identificador de la orden de pedido.
	 */
	public long getId();
	
	/**
	 * @return fecha en que se realiza la entrega de la orden de pedido.
	 */
	public Date getFechaEntrega();
	
	/**
	 * @return fecha esperada de entrega de la orden.
	 */
	public Date getFechaEsperadaEntrega();
	
	/**
	 * @return calificacion otorgada al proveedor por el estado de los productos en la orden.
	 */
	public double getCalificacionProveedor();
	
	/**
	 * @return identificacion del proveedor al que se le hace la orden.
	 */
	public String getProveedor();
	
	/**
	 * @return identificacion de la sucursal que emite la orden de compra.
	 */
	public long getidSucursal();
	
	
	/**
	 * Cadena de caracteres con todos los atributos de.OrdenPedido
	 */
	@Override
	public String toString();
}
