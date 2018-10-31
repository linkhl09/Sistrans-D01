package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de promocion promSegUniDes.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * @author Andrés Hernández y Jenifer Rodriguez */

public interface VOPromocionSucursal
{

	// -----------------------------------------------------------------
    // Métodos.
    // -----------------------------------------------------------------
	
	/**
	 * @return el identificador de la promocion
	 */
	public long getIdPromocion();

	/**
	 * @param idPromocion el nuevo identificador de la promocion
	 */
	public void setIdPromocion(long idPromocion);

	/**
	 * @return el identificador de la sucursal
	 */
	public long getIdSucursal();

	/**
	 * @param idSucursal el nuevo identificador de la sucursal
	 */
	public void setIdSucursal(long idSucursal);

}
