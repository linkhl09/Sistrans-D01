package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public interface VOPromocion {

	/**
	 * Interfaz para los métodos get de promocion.
	 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
	 * @author Andrés Hernández y Jenifer Rodriguez */

	/**
	 * @return fecha de inicio de la promocion.
	 */
	public Date getFechaInicio();

	/**
	 * Asigna la fecha de inicion a una promocion.
	 * @param nueva fecha de incio.
	 */
	public void setFechaInicio(Date fechaInicio) ;

	/**
	 * @return fecha de finalizacion de la promocion.
	 */
	public Date getFechaFin();

	/**
	 * Asigna la fecha de culminacion de una promocion.
	 * @param nueva fecha de terminacion.
	 */
	public void setFechaFin(Date fechaFin);
	/**
	 * @return codigo del producto en promocion.
	 */
	public String getProducto();

	/**
	 * Asigna el codigo del producto que esta en promocion.
	 * @param ncodigo del producto.
	 */
	public void setProducto(String producto);
	
	/**
	 * @return idetificador de la promocion.
	 */
	public long getId();
	
	/**
	 * Asigna el identificador a la promocion.
	 * @param nuevo identificador.
	 */
	public void setId(long id);

	/**
	 * @return descripcion de la promocion.
	 */
	public String getDescripcion();
	
	/**
	 * Asigna la descripcion a la promocion.
	 * @param descripcion.
	 */
	public void setDescripcion(String descripcion);
	
	/**
	 * @return unidades disponibles de la promocion.
	 */
	public int getUnidadesDisponibles();

	/**
	 *asigna las unidades disponibles de la promocion .
	 * @param unidades disponibles.
	 */
	public void setUnidadesDisponibles(int unidades);
	
	/**
	 * @return unidades vendidas de la promocion.
	 */
	public int getUnidadesVendidas() ;

	/**
	 *asigna las unidades vendidas de la promocion .
	 * @param unidades vendidas.
	 */
	public void setUnidadesVendidas(int unidades);
	
		/**
		 * Cadena de caracteres con todos los atributos de Promocion.
		 */
		@Override
		public String toString();
	
}


